package com.example.hit_product.data.repository

import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.Classes
import com.example.hit_product.data.source.network.ApiService

class HomeRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun getClassByDay(date: String, type: String, token: String): DataState<Classes?> {
        return getResult {
            apiService.getClassByDay(date, type, token).data
        }
    }
}