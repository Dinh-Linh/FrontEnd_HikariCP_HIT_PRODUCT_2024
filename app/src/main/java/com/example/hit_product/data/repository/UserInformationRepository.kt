package com.example.hit_product.data.repository


import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.UserInformation
import com.example.hit_product.data.source.network.ApiService

class UserInformationRepository(
    private val apiService: ApiService
) : BaseRepository() {
    suspend fun getUserInformation(token: String): DataState<UserInformation?>{
        return getResult {
            apiService.getUserInformation(token).data
        }

    }
}