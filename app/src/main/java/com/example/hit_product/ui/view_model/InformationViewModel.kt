package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.repository.InformationRepository
import com.example.hit_product.data.Information
import com.example.hit_product.data.source.network.APIService
import com.example.hit_product.data.source.network.RetrofitClient

class InformationViewModel : BaseViewModel(){
    private val informationRepository = InformationRepository(
        RetrofitClient.getInstance().create(APIService::class.java)
    )

    private val _userInformation = MutableLiveData<List<Information>>()
    val userInformation:LiveData<List<Information>> get() = _userInformation

    fun getUserInformation(token: String){
        executeTask(
            request = {
                informationRepository.getUserInformation(token)
            },
            onSuccess = {
                _userInformation.value = it
                Log.d("InformationViewModel", it.toString())
            },
            onError = {
                Log.d("InformationViewModel", it.toString())

            }
        )
    }

}