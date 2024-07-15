package com.example.hit_product.data

import java.util.Date

data class ClassRegistration(
    val id : String ?= null,
    val name : String ?= null,
    val details : String ?= null,
    val leader : String ?= null,
    val startDate : Date ?= null,
    val picture : String ?= null,
    val status : String ?= null
)
