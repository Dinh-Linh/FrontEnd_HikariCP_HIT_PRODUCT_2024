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

    fun logOut(token: String){
        executeTask(
            request = {
                settingRepository.logOut(token)
            },
            onSuccess = {
                val sharedPreferences = MyApplication.context.getSharedPreferences("account", MODE_PRIVATE)
                sharedPreferences.edit().remove("token").commit()
            },
            onError = {
                Log.e("LogOut Error: ", it.message.toString())
            },
        )
    }
}