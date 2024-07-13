package com.example.hit_product.ui.view_model

import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.repository.LoginRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.LoginRequest
import com.example.hit_product.data.source.network.LoginResponse
import com.example.hit_product.data.source.network.RetrofitClient

class LoginViewModel : BaseViewModel() {
    private val loginRepo = LoginRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    val error = MutableLiveData<String>(null)
    fun login(loginRequest: LoginRequest, onLoginSuccess : (ApiResponse<LoginResponse>) ->Unit){
        executeTask(
            request = {
                loginRepo.login(loginRequest)
            },
            onSuccess = { response ->
                onLoginSuccess(response)
            },
            onError = {exception ->
                error.value = exception.message

            }
        )
    }
}

