package com.example.hit_product.data

import java.io.Serializable
import java.util.Date

data class CourseRegistration(
    val id : String ?= null,
    val name : String,
    val detail : String ?= null,
    val leader : String,
    val startDate : Date ?= null,
    val picture : String ?= null,
    val status : String ?= null
) : Serializable
