package com.example.hit_product.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.base.DataState
import com.example.hit_product.data.repository.OTPRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.OTPRequest
import com.example.hit_product.data.source.network.OTPResponse
import com.example.hit_product.data.source.network.RetrofitClient
import kotlinx.coroutines.launch

class OTPViewModel : BaseViewModel(){
    private val otpRepo = OTPRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun verifyOTP(otpRequest: OTPRequest, onOtpSuccess: (ApiResponse<OTPResponse>)-> Unit){
        viewModelScope.launch {
            try{

            }catch (e: Exception){
                _error.value = e.message
            }
        }
    }

}