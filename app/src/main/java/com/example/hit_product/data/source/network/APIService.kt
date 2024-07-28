package com.example.hit_product.data.source.network

import androidx.lifecycle.LiveData
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.data.data_class.*
import com.example.hit_product.utils.constant.APIConstant
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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
    ): ApiResponse<UserInformation?>

    @POST(APIConstant.EndPoint.FORGOT_PASSWORD)
    suspend fun sendOTP(
        @Body emailRequest: EmailRequest
    ):ApiResponse<EmailResponse>


    @POST(APIConstant.EndPoint.CONFIRM_OTP)
    suspend fun verifyOTP(
        @Body otpRequest: OTPRequest
    ):ApiResponse<OTPResponse>

    @GET(APIConstant.EndPoint.LIST_COURSE)
    suspend fun getAllCourse(@Header("Authorization") token: String): ApiResponse<CourseResponse>

    @POST(APIConstant.EndPoint.REGISTER_COURSE)
    suspend fun registerCourse(
        @Header("Authorization") token: String,
        @Query("subscriberId") subscriberId: String,
        @Query("courseId") courseId: String
    ) : ApiResponse<RegisterCourseResponse>

    @POST(APIConstant.EndPoint.LOGOUT)
    suspend fun logout(@Header("Authorization") token: String) : ApiResponse<LogoutResponse>

}