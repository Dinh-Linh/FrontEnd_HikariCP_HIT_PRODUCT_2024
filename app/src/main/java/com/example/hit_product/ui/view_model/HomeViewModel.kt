package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.Classes
import com.example.hit_product.data.repository.HomeRepository
import com.example.hit_product.data.source.network.APIService
import com.example.hit_product.data.source.network.RetrofitClient


class HomeViewModel : BaseViewModel(){
    private val homeRepository = HomeRepository(
        RetrofitClient.getInstance().create(APIService::class.java)
    )
    private val _classes = MutableLiveData<List<Classes>>()
    val classes: LiveData<List<Classes>> get() = _classes
    fun getAllClass(token : String){
        executeTask(
            request = {
                homeRepository.getAllClass(token)
            },
            onSuccess = {
                _classes.value = it
                Log.d("HomeViewModel", it.toString())
            },
            onError = {
                Log.d("HomeViewModel", it.toString())
            }
        )
    }
}