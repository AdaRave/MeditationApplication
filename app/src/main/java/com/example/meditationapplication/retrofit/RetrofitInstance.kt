package com.example.meditationapplication.retrofit

import com.example.meditationapplication.ui.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : RetrofitInterface by lazy {
        retrofit.create(RetrofitInterface::class.java)
    }
}