package com.example.hit_product.data.source.local

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Converters {
    fun fromTimestamp(value:Long?) : Date?{
        return value?.let { Date(it) }
    }

    fun dateToTimestamp(date: Date?): Long?{
        return date?.time
    }

    fun getCurrentDate(currentDate: Date) : String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(currentDate)
    }
}