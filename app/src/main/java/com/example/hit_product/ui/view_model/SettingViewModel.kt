package com.example.hit_product.ui.view_model

import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.hit_product.MyApplication
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.repository.SettingRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class SettingViewModel : BaseViewModel() {
    private val settingRepository = SettingRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    fun logout(token: String){
        executeTask(
            request = {
                settingRepository.logout(token)
            },
            onSuccess = {
                val sharedPreferences = MyApplication.context.getSharedPreferences("account", MODE_PRIVATE)
                sharedPreferences.edit().remove("token").commit()
                Log.d("Token: ", it.toString())
            },
            onError = {
                Log.e("LogOut Error: ", it.message.toString())
            },
        )
    }
}