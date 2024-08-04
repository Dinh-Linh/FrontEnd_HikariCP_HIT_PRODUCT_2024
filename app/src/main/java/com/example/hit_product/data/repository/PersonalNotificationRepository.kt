package com.example.hit_product.data.repository

import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.data_class.GeneralNotification
import com.example.hit_product.data.source.network.ApiService

class PersonalNotificationRepository (private val apiService: ApiService): BaseRepository(){
    suspend fun getPersonalNotification(memberId: String, token: String) : DataState<List<GeneralNotification>>{
        return getResult {
            apiService.getPersonalNotification(memberId, token).data?.items ?: emptyList()
        }
    }
}