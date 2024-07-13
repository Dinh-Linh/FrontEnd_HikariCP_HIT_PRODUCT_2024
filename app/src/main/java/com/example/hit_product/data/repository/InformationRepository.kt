package com.example.hit_product.data.repository


import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.Information
import com.example.hit_product.data.source.network.APIService

class InformationRepository(
    private val apiService: APIService
) : BaseRepository() {
    suspend fun getUserInformation(token: String):DataState<List<Information>>{
        return getResult {
            apiService.getUserInformation(token)
        }
    }
}