package com.example.hit_product.data.repository

import android.provider.ContactsContract.Data
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.data_class.OTPRequest
import com.example.hit_product.data.data_class.OTPResponse
import com.example.hit_product.data.source.network.ApiService

class OTPRepository(private val apiService: ApiService): BaseRepository() {
    suspend fun otpConfirm(otpRequest: OTPRequest) :DataState<ApiResponse<OTPResponse>>{
        return getResult {
            apiService.verifyOTP(otpRequest)
        }
    }
}