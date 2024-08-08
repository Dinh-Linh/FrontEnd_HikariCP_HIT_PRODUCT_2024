package com.example.hit_product.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import com.example.hit_product.R

class DialogChangePasswordFailure(context: Context) : Dialog(context){

    private lateinit var btnAgreeChangePassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_change_password_failure)
        btnAgreeChangePassword = findViewById(R.id.btnAgreeInChangePassword)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnAgreeChangePassword.setOnClickListener{
            dismiss()
        }
    }
}