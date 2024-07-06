package com.example.hit_product.ui

import androidx.lifecycle.LiveData
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.repository.LoginRepository
import com.example.hit_product.data.source.Members
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class LoginViewModel : BaseViewModel() {
    private val loginRepo = LoginRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

}

