package com.example.hit_product.data.source.local

import java.util.Date

class Converters {
    fun fromTimestamp(value:Long?) : Date?{
        return value?.let { Date(it) }
    }

    fun dateToTimestamp(date: Date?): Long?{
        return date?.time
    }
}