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
        const val SCHEDULE = "api/schedule/events/class"
        const val GETEVENTBYDATE = "api/schedule/events/date"
    }
}