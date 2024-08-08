package com.example.hit_product.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.hit_product.R

class CustomViewToast(context: Context) : Toast(context) {
    companion object {

        const val SHORT = 4000L  // Use Long type for duration
        const val LONG = 7000L
    }

    @SuppressLint("ResourceType")
    fun makeText(context: Context, message: String, duration: Long, imageResource: Int): Toast {
        val toast = Toast(context)
        val layout =
            LayoutInflater.from(context).inflate(R.layout.custom_toast, null, false) as View
        val l1 = layout.findViewById<TextView>(R.id.textToast)
        val img = layout.findViewById<ImageView>(R.id.imgToast)
        l1.text = message
        img.setImageResource(imageResource)
        toast.view = layout
        toast.duration = if (duration == LONG) LENGTH_LONG else LENGTH_SHORT
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, dpToPx(context, 120))
        return toast
    }
    private fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }
}