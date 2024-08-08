package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.data.repository.PersonalNotificationRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class PersonalNotificationViewModel : BaseViewModel(){
    private val listPersonalNotiRepo = PersonalNotificationRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    private val _listPersonalNotification = MutableLiveData<List<GeneralNotification>>()

    val listPersonalNotification: LiveData<List<GeneralNotification>> get() = _listPersonalNotification

    fun getPersonalNotification(memberId: String, token: String){
        executeTask(
            request ={
                listPersonalNotiRepo.getPersonalNotification(memberId, token)
            },
            onSuccess = {
                _listPersonalNotification.value = it
                Log.d("Personal Notification", it.toString())
            },
            onError = {
                Log.d("Error", "${error(it)}")
            }
        )
    }
}