package com.example.hit_product.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import com.example.hit_product.R

class DialogEmailOTPFailure(context: Context) : Dialog(context) {
    private lateinit var btnAgreeOTP: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_email_otp_failure)
        btnAgreeOTP = findViewById(R.id.btnAgreeInEmail)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnAgreeOTP.setOnClickListener{
            dismiss()
        }
    }

}