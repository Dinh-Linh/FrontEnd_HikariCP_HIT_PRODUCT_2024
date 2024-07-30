package com.example.hit_product.data.data_class

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

data class RegisterCourseResponse(val memberId: String, val courseId: String, val status: String)
data class RegisterCourseRequest(val userId: String, val courseId: String)
