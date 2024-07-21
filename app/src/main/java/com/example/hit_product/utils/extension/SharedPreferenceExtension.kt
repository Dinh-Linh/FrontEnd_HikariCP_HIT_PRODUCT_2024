package com.example.hit_product.utils.extension

import android.app.Activity
import android.content.Context.MODE_PRIVATE

fun Activity.getToken() : String?{
    val preferences = getSharedPreferences("account", MODE_PRIVATE)
    return  preferences.getString("token", null)
}