package com.example.hit_product.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import com.example.hit_product.R

class DialogLoginFailure(context: Context) : Dialog(context) {

    private lateinit var btnAgree: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_login_failure)
        btnAgree = findViewById(R.id.btnAgree)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnAgree.setOnClickListener {
            dismiss() // Tắt dialog khi click
        }
    }
}
