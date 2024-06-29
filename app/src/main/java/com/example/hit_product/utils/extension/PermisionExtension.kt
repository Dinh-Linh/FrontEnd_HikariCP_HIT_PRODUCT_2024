package com.example.hit_product.utils.extension

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


fun Context?.checkHasPermission(namePermission: String): Boolean{
    this?.let {
        return (ActivityCompat.checkSelfPermission(
            it,
            namePermission
        ) == PackageManager.PERMISSION_GRANTED)
    } ?: return false
}

fun Context?.checkHasPermission(permission: List<String>): Boolean{
    this?.let {
        return permission.all { permission -> checkHasPermission(permission) }
    } ?: return false
}

fun Activity?.requestPermission(permission: Array<String>){
    this?.let {
        ActivityCompat.requestPermissions(
            it,
            permission,
            1
        )
    }
}