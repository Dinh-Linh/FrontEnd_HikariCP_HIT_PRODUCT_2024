package com.example.hit_product.data.repository

import android.provider.ContactsContract.Data
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.data_class.ChangePasswordRequest
import com.example.hit_product.data.data_class.ChangePasswordResponse
import com.example.hit_product.data.source.network.ApiService

class ChangePasswordRepository (private val apiService: ApiService) : BaseRepository(){
    suspend fun changePasswordConfirm(changePasswordRequest: ChangePasswordRequest) : DataState<ApiResponse<ChangePasswordResponse>>{
        return getResult {
            apiService.changeUserPassword(changePasswordRequest)
        }
    }
}