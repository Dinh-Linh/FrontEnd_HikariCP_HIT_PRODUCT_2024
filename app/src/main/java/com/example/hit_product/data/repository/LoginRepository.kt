package com.example.hit_product.data.repository

import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.source.network.APIService
import com.example.hit_product.data.source.network.LoginRequest
import com.example.hit_product.data.source.network.LoginResponse

class LoginRepository(private val apiService: APIService) : BaseRepository() {
    suspend fun login(loginRequest: LoginRequest) : DataState<ApiResponse<LoginResponse>> {
        return getResult {
            apiService.login(loginRequest)
        }
    }
}