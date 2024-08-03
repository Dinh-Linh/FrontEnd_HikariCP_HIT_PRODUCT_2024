package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.Notification
import com.example.hit_product.data.repository.NotificationRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class NotificationViewModel : BaseViewModel(){
    private val listGeneralNotiRepo = NotificationRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    private val _listNotification = MutableLiveData<List<Notification>>()
    val listGeneralNotification: LiveData<List<Notification>> get() = _listNotification
    fun getGeneralNotification(token: String){
        executeTask(
            request = {
                listGeneralNotiRepo.getGeneralNotification(token)
            },
            onSuccess = {
                _listNotification.value = it
                Log.d("General Notification", it.toString())
            },
            onError = {
                Log.e("Error", "${error(it)}")
            }
        )
    }
}