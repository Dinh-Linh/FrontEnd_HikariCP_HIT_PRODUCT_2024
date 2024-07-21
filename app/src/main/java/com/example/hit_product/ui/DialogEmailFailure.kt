package com.example.hit_product.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import com.example.hit_product.R

class DialogEmailFailure(context: Context) : Dialog(context){

    private lateinit var btnAgreeEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_email_failure)
        btnAgreeEmail = findViewById(R.id.btnAgreeInEmail)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnAgreeEmail.setOnClickListener{
            dismiss()
        }
    }
}