package com.example.hit_product.base

import android.service.quickaccesswallet.GetWalletCardsRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    protected fun <T> executeTask(
        request: suspend CoroutineScope.() -> DataState<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        showLoading:Boolean = true
    ){
        viewModelScope.launch {
            if(showLoading){
                showLoading()
            }
            when(val response = request(this)){
                is DataState.Success -> {
                    onSuccess(response.data)
                    hideLoading()
                }
                is DataState.Error -> {
                    onError(response.exception)
                    hideLoading()
                }
            }
        }
    }

    fun showLoading() {
        _loading.value = true
    }

    fun hideLoading() {
        _loading.value = false
    }
}