package com.example.androidminiapp.services

import com.example.androidminiapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(Interceptor {chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization","Client-ID " +"qmcGUm8HlLxpdTkxWZn-ufF9tUOpIB2OxbGG7_czNGY")                    .build()
                chain.proceed(request)
            })
            .build()
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val api : ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}