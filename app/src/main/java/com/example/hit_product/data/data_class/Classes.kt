package com.example.hit_product.data.data_class

import java.util.Date

data class Classes(
    val name: String,
    val type: String ?= null,
    val location: String,
    val startDate: Date? = null,
    val endDate: Date? = null,
    val startTime: Date,
    val endTime: Date
)
