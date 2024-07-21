package com.example.hit_product.data.repository

import android.util.Log
import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.data_class.LogoutResponse
import com.example.hit_product.data.source.network.ApiService

class SettingRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun logout(token : String) : DataState<LogoutResponse>{
        return getResult {
            Log.d("SettingRepository", "Calling API logout")
            apiService.logout(token).data!!
        }
    }
}