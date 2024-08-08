package com.example.hit_product.data.repository

import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.data.source.network.ApiService

class GeneralNotificationRepository(private val apiService: ApiService): BaseRepository() {
    suspend fun getGeneralNotification(token : String) : DataState<List<GeneralNotification>>{
        return getResult {
            apiService.getGeneralNotification(token).data?.items ?: emptyList()
        }
    }
}