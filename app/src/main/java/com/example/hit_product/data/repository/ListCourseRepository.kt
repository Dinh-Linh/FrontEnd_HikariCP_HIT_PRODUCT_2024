package com.example.hit_product.data.repository

import com.example.hit_product.base.BaseRepository
import com.example.hit_product.base.DataState
import com.example.hit_product.data.CourseRegistration
import com.example.hit_product.data.CourseResponse
import com.example.hit_product.data.source.network.ApiService

class ListCourseRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun getAllCourse(token : String) : DataState<List<CourseRegistration>>{
        return getResult {
            apiService.getAllCourse(token).data?.item ?: emptyList()
        }
    }
}