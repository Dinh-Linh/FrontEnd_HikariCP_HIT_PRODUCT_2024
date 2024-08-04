package com.example.hit_product.data.data_class

data class NotificationResponse(
    val meta: MetaNotification,
    val items: List<GeneralNotification>
)
data class MetaNotification(
    val totalElements: Int,
    val totalPages: Int,
    val pageNum: Int,
    val pageSize: Int,
    val sortBy: String?,
    val sortType: String?
)
data class PersonalNotifyResponse(
    val meta: MetaNotification,
    val items: List<GeneralNotification>
)