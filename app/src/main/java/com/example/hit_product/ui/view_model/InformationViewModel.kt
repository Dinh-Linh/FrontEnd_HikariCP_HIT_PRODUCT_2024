package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.repository.UserInformationRepository
import com.example.hit_product.data.UserInformation
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class InformationViewModel : BaseViewModel(){
    private val informationRepository = UserInformationRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    private val _userInformation = MutableLiveData<UserInformation>()
    val userInformation: LiveData<UserInformation> get() = _userInformation

    fun getUserInformation(token: String){
        executeTask(
            request = {
                informationRepository.getUserInformation(token)
            },
            onSuccess = {
                _userInformation.value = it
                Log.d("InformationViewModel", "Success: $it")
            },
            onError = {error ->
                Log.d("InformationViewModel", "Error: $error" )

            }
        )
    }

}