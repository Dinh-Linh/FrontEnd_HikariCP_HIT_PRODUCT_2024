package com.example.hit_product.ui.view_model

import android.app.Notification
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.Classes
import com.example.hit_product.data.repository.HomeRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient
private const val TAG = "HomeViewModel"
class HomeViewModel : BaseViewModel() {
    private val homeRepository = HomeRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )
    private val _classes = MutableLiveData<Classes>()

    val classes: LiveData<Classes> get() = _classes

    fun getClassByDay(date: String, type: String, token: String) {
        executeTask(
            request = {
                homeRepository.getClassByDay(date, type, token)
            },
            onSuccess = {
                _classes.value = it // Lấy dữ liệu từ response.data
                Log.d("HomeViewModel", "Class: $it")
            },
            onError = { error ->
                Log.e("HomeViewModel", "Error: $error")
            }
        )
    }

}