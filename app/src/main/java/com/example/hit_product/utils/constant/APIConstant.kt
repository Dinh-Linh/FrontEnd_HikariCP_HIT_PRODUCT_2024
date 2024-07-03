package com.example.hit_product.utils.constant


object APIConstant {
    const val BASE_URL = "http://103.195.236.162:8080/swagger-ui/index.html#/"
    object TimeOut{
        const val CONNECT_TIME_OUT = 20L
    }
    object EndPoint {
        const val MEMBERS = "members"
        const val MEMBER_BY_ID = "members/{id}"
    }
}