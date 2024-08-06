package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.CancelRegisteredResponse
import com.example.hit_product.data.data_class.RegisterCourseRequest
import com.example.hit_product.data.data_class.RegisterCourseResponse
import com.example.hit_product.data.data_class.SimpleRegistered
import com.example.hit_product.data.repository.RegistrationRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class RegistrationViewModel : BaseViewModel() {
    private val registrationRepository = RegistrationRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    private val _listRegistered = MutableLiveData<List<SimpleRegistered>>()
    val listRegistered: LiveData<List<SimpleRegistered>> get() = _listRegistered

    private val _countRegistered = MutableLiveData<Int>()
    val countRegistered: LiveData<Int> get() = _countRegistered

    private val _countAcceptRegistered = MutableLiveData<Int>()
    val countAcceptRegistered : LiveData<Int> get() = _countAcceptRegistered
    fun registerCourse(
        token: String,
        registerCourseRequest: RegisterCourseRequest,
        onRegisterCourseResponse: (RegisterCourseResponse) -> Unit
    ) {
        executeTask(
            request = {
                registrationRepository.registerCourse(token, registerCourseRequest)
            },
            onSuccess = { response ->
                onRegisterCourseResponse(response)

            },
            onError = {
                Log.e("Error: ", it.message!!)
            }
        )
    }

    fun getRegisteredByName(token: String, username: String) {
        executeTask(
            request = {
                registrationRepository.getRegisteredByName(token, username)
            },
            onSuccess = { response ->
                _listRegistered.value = response.map { simpleRegisterCourseResponse ->
                    SimpleRegistered(
                        id = simpleRegisterCourseResponse.id,
                        courseId = simpleRegisterCourseResponse.course.id!!,
                        status = simpleRegisterCourseResponse.status,
                        name = simpleRegisterCourseResponse.course.name,
                        leader = simpleRegisterCourseResponse.course.leader
                    )
                }
                _countRegistered.value = response.size

                _countAcceptRegistered.value = response.count{it.status == "ACCEPT"}
            },
            onError = {
                Log.e("Error: ", "${error(it)}")
            }
        )
    }

    fun cancelRegistered(
        token: String,
        registerId: String,
        memberId: String,
        onCancelRegisteredResponse: (CancelRegisteredResponse) -> Unit
    ) {
        executeTask(
            request = {
                registrationRepository.cancelRegistered(token, registerId, memberId)
            },
            onSuccess = { response ->
                onCancelRegisteredResponse(response)
                _listRegistered.value = _listRegistered.value?.filterNot { it.id == registerId }
                _countRegistered.value = _listRegistered.value?.size
            },
            onError = {
                Log.e("Error: ", it.message!!)
            }
        )
    }
}