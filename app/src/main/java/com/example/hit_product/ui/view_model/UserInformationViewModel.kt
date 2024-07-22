package com.example.hit_product.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.repository.UserInformationRepository
import com.example.hit_product.data.data_class.UserInformation
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class UserInformationViewModel : BaseViewModel(){
    private val userInformationRepository = UserInformationRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    private val _userInformation = MutableLiveData<UserInformation>()
    val userInformation: LiveData<UserInformation> get() = _userInformation

    fun getUserInformation(token: String){
        executeTask(
            request = {
                userInformationRepository.getUserInformation(token)
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