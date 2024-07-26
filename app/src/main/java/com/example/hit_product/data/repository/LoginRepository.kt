package com.example.hit_product.data.repository

import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.data_class.LoginRequest
import com.example.hit_product.data.data_class.LoginResponse
import com.example.hit_product.data.source.network.ApiService


class LoginRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun login(loginRequest: LoginRequest) : DataState<ApiResponse<LoginResponse>>{
        return getResult {
            apiService.login(loginRequest)
        }
    }
}