package com.example.hit_product.data.repository

import com.example.hit_product.base.DataState
import com.example.hit_product.data.source.network.ApiService
import com.example.hit_product.data.source.network.LoginRequest
import com.example.hit_product.data.source.network.LoginResponse
import java.lang.Exception

class LoginRepository(private val apiService: ApiService) {
    suspend fun login(username:String, password: String) : DataState<LoginResponse>{
        return try{
            val response = apiService.login(LoginRequest(username, password)).execute()
            if (response.isSuccessful){
                response.body()?.let {
                    DataState.Success(it)
                } ?: DataState.Error(exception = Exception("Error"))
            }
            else{
                DataState.Error(Exception(response.errorBody()?.string()))
            }
        }
        catch (e: Exception){
            DataState.Error(e)
        }
    }
}