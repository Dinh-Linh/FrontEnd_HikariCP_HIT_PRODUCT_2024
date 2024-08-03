package com.example.hit_product.ui.view_model

import androidx.lifecycle.MutableLiveData
import com.example.hit_product.base.ApiResponse
import com.example.hit_product.base.BaseViewModel
import com.example.hit_product.data.data_class.EmailRequest
import com.example.hit_product.data.data_class.EmailResponse
import com.example.hit_product.data.repository.EmailRepository
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.RetrofitClient

class EmailViewModel : BaseViewModel(){
    private val emailRepo = EmailRepository(
        RetrofitClient.getInstance().create(ApiService::class.java)
    )

    val error = MutableLiveData<String>(null)

    fun email(emailRequest: EmailRequest, onEmailSuccess: (ApiResponse<EmailResponse>) -> Unit){
        executeTask(
            request = {
                emailRepo.sendOTP(emailRequest)
            },
            onSuccess = { response ->
                onEmailSuccess(response)
            },
            onError = {exception ->
                error.value = exception.message
            }
        )}
}