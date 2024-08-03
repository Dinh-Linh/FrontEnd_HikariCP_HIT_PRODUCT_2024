package com.example.hit_product.utils.constant


object APIConstant {
    const val BASE_URL = "http://103.195.236.162:8080/"
    object TimeOut{
        const val CONNECT_TIME_OUT = 20L
    }
    object EndPoint {
        const val MEMBERS = "member"
        const val MEMBER_BY_ID = "member/{id}"
        const val LOGIN = "api/v1/auth/login"
        const val CLASS = "user/events/date"
        const val GETEVENTBYDATE = "api/schedule/events/date"
        const val CHANGE_PASSWORD = "api/v1/member/changePassword"
        const val USER_INFORMATION = "api/v1/user/current"
        const val FORGOT_PASSWORD = "api/v1/auth/forgot-password"
        const val CONFIRM_OTP = "api/v1/auth/confirm_otp"
        const val LIST_COURSE = "user/course"
        const val REGISTER_COURSE = "user/register"
        const val LOGOUT = "api/v1/auth/logout"
        const val NOTIFICATION = "/user/notification/general"
    }
}