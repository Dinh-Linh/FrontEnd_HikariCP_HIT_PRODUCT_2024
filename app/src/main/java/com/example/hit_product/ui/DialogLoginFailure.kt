package com.example.hit_product.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.hit_product.R

class DialogLoginFailure(context: Context) : Dialog(context) {

    private lateinit var btnAgree: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_login_failure)
        btnAgree = findViewById(R.id.btnAgree)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnAgree.setOnClickListener {
            dismiss()// Tắt dialog khi click
        }
        if (!checkNetwork(this.context)) {
            findViewById<TextView>(R.id.bugLogin).text = "Vui lòng kiểm tra lại kết nối mạng"
            Log.d("Wifi: ", "Is not available")
        } else {
            findViewById<TextView>(R.id.bugLogin).text = "Vui lòng kiểm tra lại thông tin đăng nhập"
            Log.d("Wifi: ", "Is available")
        }
    }

    @Suppress("DEPRECATION")
    private fun checkNetwork(context: Context): Boolean {
        val connectManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val network = connectManager.activeNetwork ?: return false
            val activeNetwork = connectManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }

        } else {
            val networkInfo = connectManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}
