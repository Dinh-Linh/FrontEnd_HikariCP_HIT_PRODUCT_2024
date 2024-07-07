package com.example.hit_product.data.source.network

import com.example.hit_product.base.ApiResponse
import com.example.hit_product.utils.constant.APIConstant
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val emailOrPhone: String, val password: String)
data class LoginResponse(val accessToken: String)

interface ApiService {
    @POST(APIConstant.EndPoint.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>
}