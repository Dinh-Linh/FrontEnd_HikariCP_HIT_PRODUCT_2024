package com.example.hit_product.utils.extension

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.example.hit_product.databinding.DialogComonLoadingBinding


fun Dialog.showLoading(
    binding: ViewBinding = DialogComonLoadingBinding.inflate(layoutInflater),
    isTouchOutSideCancelable: Boolean = false,
    marginY:Int = -170
) {
    setContentView(binding.root)
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        attributes.apply {
            y = marginY
            gravity = Gravity.CENTER
        }
    }
    setCancelable(isTouchOutSideCancelable)
    setCanceledOnTouchOutside(false)
    show()
}