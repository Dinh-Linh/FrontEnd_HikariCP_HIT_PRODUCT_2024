package com.example.hit_product.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hit_product.R

class ToastView(context: Context) : Toast(context) {

    @SuppressLint("InflateParams")
    fun makeText(context: Context, message: String): Toast {
        val toast = Toast(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.activity_toast_view, null) as View
        val textT = layout.findViewById<TextView>(R.id.textToast)
        textT.text = message
        toast.view = layout
        toast.duration = Toast.LENGTH_SHORT
        return toast
    }
}