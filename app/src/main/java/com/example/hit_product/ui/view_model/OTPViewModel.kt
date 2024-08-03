package com.example.hit_product.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.OTPRequest
import com.example.hit_product.data.data_class.OTPResponse
import com.example.hit_product.data.repository.OTPRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient
import kotlinx.coroutines.launch

class OTPViewModel : BaseViewModel(){
    private val otpRepo = OTPRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )
    val error = MutableLiveData<String>(null)

    fun otp(otpRequest: OTPRequest, onOTPSuccess: (ApiResponse<OTPResponse>)->Unit){
        executeTask(
            request = {
                otpRepo.otpConfirm(otpRequest)
            },
            onSuccess = {response ->
                onOTPSuccess(response)

            },
            onError = {exception ->
                error.value = exception.message
            }

        )
    }

}