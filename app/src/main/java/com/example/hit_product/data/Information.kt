package com.example.hit_product.data

data class Information(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String,
    val avatar: String,
    val phone: String,
    val address: String,
    val className: String,
    val birth: String,
    val gen: String,
    val numberCourse: Int,
    val status: String,
    val qr: String,
    val role: Role
)

data class Role(
    val id: Int,
    val name: String
)
