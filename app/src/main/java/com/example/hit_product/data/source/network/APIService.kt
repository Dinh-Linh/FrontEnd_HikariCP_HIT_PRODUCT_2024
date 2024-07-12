package com.example.hit_product.data.source.network

import com.example.hit_product.base.ApiResponse
import com.example.hit_product.data.Classes
import com.example.hit_product.utils.constant.APIConstant
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

data class LoginRequest(val emailOrPhone: String, val password: String)
data class LoginResponse(val accessToken: String)

interface ApiService {
    @POST(APIConstant.EndPoint.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>

    @GET(APIConstant.EndPoint.SCHEDULE)
    suspend fun getAllClass(@Header("Authorization") token : String) : List<Classes>

    @GET(APIConstant.EndPoint.SCHEDULE)
    suspend fun getClassByDay(@Query("day") day : String,@Header("Authorization") token: String) : List<Classes>

    @GET(APIConstant.EndPoint.GETEVENTBYDATE)
    suspend fun getEventByDate(@Query("day") day : String,@Header("Authorization") token: String) : List<Classes>
}