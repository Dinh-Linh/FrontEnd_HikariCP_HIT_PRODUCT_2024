package com.example.hit_product.base

data class ApiResponse<T>(
    val status: String,
    val data: T
)

