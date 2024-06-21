package com.example.hit_product.data.source.network

import com.example.hit_product.utils.constant.APIConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    @Volatile
    private var INSTACE : Retrofit? = null

    fun getInstance() : Retrofit =
        INSTACE ?: synchronized(this){
            val instance = retrofitBuilder()
            INSTACE = instance
            instance
        }

    private fun retrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provodeOkHttpClient())
            .build()
    }

    private fun provodeOkHttpClient(): OkHttpClient {
        val intereptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient
            .connectTimeout(APIConstant.Timeout.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(intereptor)
        return okHttpClient.build()
    }

}