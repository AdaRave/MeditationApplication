package com.example.meditationapplication.retrofit

import com.example.meditationapplication.login.model.ModelLogin
import com.example.meditationapplication.login.model.ModelLoginPost
import com.example.meditationapplication.main.model.ModelFeelings
import com.example.meditationapplication.main.model.ModelQuotes
import retrofit2.Response

class Repository {
    suspend fun login(modelLoginPost: ModelLoginPost): Response<ModelLogin> {
        return RetrofitInstance.api.login(modelLoginPost)
    }

    suspend fun feelings(): Response<ModelFeelings> {
        return RetrofitInstance.api.feelings()
    }

    suspend fun quotes(): Response<ModelQuotes> {
        return RetrofitInstance.api.quotes()
    }

    //view model to pass the value to fragment
    fun loginTransit(modelLogin: ModelLogin): ModelLogin {
        return modelLogin
    }
}