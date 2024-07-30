package com.example.hit_product.ui.view_model

import android.util.Log
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.RegisterCourseRequest
import com.example.hit_product.data.data_class.RegisterCourseResponse
import com.example.hit_product.data.repository.RegistrationRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class RegistrationViewModel : BaseViewModel() {
    private val registrationRepository = RegistrationRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    fun registerCourse(token: String, registerCourseRequest: RegisterCourseRequest, onRegisterCourseResponse: (RegisterCourseResponse) -> Unit) {
        executeTask(
            request = {
                registrationRepository.registerCourse(token, registerCourseRequest)
            },
            onSuccess = {response ->
                onRegisterCourseResponse(response)

            },
            onError = {
                Log.e("Error RegistrationViewModel: ", error(it))
            }
        )
    }
}