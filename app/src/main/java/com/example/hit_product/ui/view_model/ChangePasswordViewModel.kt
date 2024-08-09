package com.example.hit_product.ui.view_model

import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.ChangePasswordRequest
import com.example.hit_product.data.data_class.ChangePasswordResponse
import com.example.hit_product.data.repository.ChangePasswordRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class ChangePasswordViewModel : BaseViewModel(){
    private val changePassRepo = ChangePasswordRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )
    val error = MutableLiveData<String>(null)

    fun changePassword(changePasswordRequest: ChangePasswordRequest, onChangePWSuccess: (ApiResponse<ChangePasswordResponse>) -> Unit){
        executeTask(
            request = {
                changePassRepo.changePasswordConfirm(changePasswordRequest)
            },
            onSuccess = {response ->
                onChangePWSuccess(response)
            },
            onError = {exception ->
                error.value = exception.message
            }
        )
    }
}