package com.example.hit_product.data

data class CourseResponse(
    val meta : Meta,
    val items : List<CourseRegistration>
)

data class Meta(
    val totalElements: Int,
    val totalPages: Int,
    val pageNum: Int,
    val pageSize: Int,
    val sortBy: String?,
    val sortType: String?
)
