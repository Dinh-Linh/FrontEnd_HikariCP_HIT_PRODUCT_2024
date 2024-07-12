package com.example.hit_product.data.repository

import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.Classes
import com.example.hit_product.data.source.network.ApiService

class HomeRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun getAllClass(token : String) : DataState<List<Classes>>{
        return getResult {
            apiService.getAllClass(token)
        }
    }
}