package com.example.hit_product.data.source.network

import com.example.hit_product.base.ApiResponse
import com.example.hit_product.data.Classes
import com.example.hit_product.data.Information
import com.example.hit_product.utils.constant.APIConstant
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val accessToken: String)


interface ApiService {
    @POST(APIConstant.EndPoint.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>

    @GET(APIConstant.EndPoint.CLASS)
    suspend fun getAllClass(@Header("Authorization") token: String): List<Classes>

    @GET(APIConstant.EndPoint.CLASS)
    suspend fun getClassByDay(
        @Query("date") date: String,
        @Query("type") type: String,
        @Header("Authorization") token: String
    ): ApiResponse<Classes?>

    @GET(APIConstant.EndPoint.GETEVENTBYDATE)
    suspend fun getEventByDate(
        @Query("day") day: String,
        @Header("Authorization") token: String
    ): List<Classes>

    @GET(APIConstant.EndPoint.USER_INFORMATION)
    suspend fun getUserInformation(
        @Header("Authorization") token: String
    ):ApiResponse<Information?>


}