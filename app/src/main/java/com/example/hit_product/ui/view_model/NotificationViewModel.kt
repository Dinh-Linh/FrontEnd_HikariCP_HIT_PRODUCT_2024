package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.data.repository.GeneralNotificationRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class NotificationViewModel : BaseViewModel(){
    private val listGeneralNotiRepo = GeneralNotificationRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    private val _listGeneralNotification = MutableLiveData<List<GeneralNotification>>()
    val listGeneralGeneralNotification: LiveData<List<GeneralNotification>> get() = _listGeneralNotification

    fun getGeneralNotification(token: String){
        executeTask(
            request = {
                listGeneralNotiRepo.getGeneralNotification(token)
            },
            onSuccess = {
                _listGeneralNotification.value = it
                Log.d("General Notification", it.toString())
            },
            onError = {
                Log.e("Error", "${error(it)}")
            }
        )
    }
}