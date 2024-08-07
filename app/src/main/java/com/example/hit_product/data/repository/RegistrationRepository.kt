package com.example.hit_product.data.repository

import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.data_class.CancelRegisteredResponse
import com.example.hit_product.data.data_class.RegisterCourseRequest
import com.example.hit_product.data.data_class.RegisterCourseResponse
import com.example.hit_product.data.data_class.RegisteredCourseResponse
import com.example.hit_product.data.source.network.ApiService

class RegistrationRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun registerCourse(
        token: String,
        registerCourseRequest: RegisterCourseRequest
    ): DataState<RegisterCourseResponse> {
        return getResult {
            apiService.registerCourse(token, registerCourseRequest).data!!
        }
    }

    suspend fun getRegisteredByName(
        token: String,
        username: String
    ): DataState<List<RegisteredCourseResponse>> {
        return getResult {
            apiService.getRegisteredByName(token, username).data?.items ?: emptyList()
        }
    }

    suspend fun cancelRegistered(token: String, registerId: String, memberId:String): DataState<CancelRegisteredResponse>{
        return getResult {
            apiService.cancelRegistered(token, registerId, memberId).data!!
        }
    }
}