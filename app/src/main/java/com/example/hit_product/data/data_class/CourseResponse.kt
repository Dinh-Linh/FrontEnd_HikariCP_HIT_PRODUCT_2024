package com.example.hit_product.data.data_class

data class CourseResponse(
    val meta: Meta,
    val items: List<CourseRegistration>
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
data class RegisterCourseRequest(val subscriberId: String, val courseId: String)

data class CancelRegisteredResponse(val status: String, val message: String)

data class ResponseRegistered(
    val meta: Meta,
    val items: List<RegisteredCourseResponse>
)

data class RegisteredCourseResponse(
    val id: String,
    val status: String,
    val subscriber: UserInformation,
    val course: CourseRegistration
)

data class SimpleRegistered(
    val id: String,
    val status: String,
    val courseId: String,
    val name: String? = null,
    val leader: String? = null
)

