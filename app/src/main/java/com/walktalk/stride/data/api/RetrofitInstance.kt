package com.walktalk.stride.data.api

import android.annotation.SuppressLint
import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("StaticFieldLeak")
object RetrofitInstance {
    lateinit var context: Context
    private const val BASE_URL = "http://sonserver2.duckdns.org"

    fun init(context: Context) {
        this.context = context
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor(context)) // 이 부분에 Interceptor 추가
                    .build()
            )
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}