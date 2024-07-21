package com.example.hit_product.data.repository

import android.provider.ContactsContract.Data
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.OTPRequest
import com.example.hit_product.data.source.network.OTPResponse

class OTPRepository(private val apiService: ApiService): BaseRepository() {
    suspend fun otpConfirm(otpRequest: OTPRequest) :DataState<ApiResponse<OTPResponse>>{
        return getResult {
            apiService.verifyOTP(otpRequest)
        }
    }
}