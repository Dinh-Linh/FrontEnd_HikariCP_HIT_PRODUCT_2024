package com.example.hit_product.data.data_class

data class OTPRequest(val email: String, val otp: String, val newPassword: String)
data class EmailRequest(val email: String)
data class LoginRequest(val username: String, val password: String)
data class ChangePasswordRequest(val memberId: String, val oldPassword: String, val newPassword: String)
