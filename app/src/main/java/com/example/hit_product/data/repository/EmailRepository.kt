package com.example.hit_product.data.repository

import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState

import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.EmailRequest
import com.example.hit_product.data.source.network.EmailResponse
import com.example.hit_product.data.source.network.OTPResponse

class EmailRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun otpConfirm(emailRequest: EmailRequest) : DataState<ApiResponse<EmailResponse>>{
        return getResult {
            apiService.sendOTP(emailRequest)
        }
    }
}