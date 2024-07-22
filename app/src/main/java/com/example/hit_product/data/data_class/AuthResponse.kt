package com.example.hit_product.data.data_class

data class EmailResponse(val accessToken: String)
data class OTPResponse(val accessToken: String)
data class LoginResponse(val accessToken: String)
data class LogoutResponse(val status: String, val message: String)