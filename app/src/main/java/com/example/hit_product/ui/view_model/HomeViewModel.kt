package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.Classes
import com.example.hit_product.data.repository.HomeRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class HomeViewModel : BaseViewModel(){
    private val homeRepository = HomeRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    fun getAllClass(token : String){
        executeTask(
            request = {
                homeRepository.getAllClass(token)
            },
            onSuccess = {
                Log.d("HomeViewModel", it.toString())
            },
            onError = {
                Log.d("HomeViewModel", it.toString())
            }
        )
    }
}