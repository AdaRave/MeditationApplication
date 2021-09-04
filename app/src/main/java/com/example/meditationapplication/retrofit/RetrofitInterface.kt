package com.example.meditationapplication.retrofit

import com.example.meditationapplication.login.model.ModelLogin
import com.example.meditationapplication.login.model.ModelLoginPost
import com.example.meditationapplication.main.model.DataFeelings
import com.example.meditationapplication.main.model.ModelFeelings
import com.example.meditationapplication.main.model.ModelQuotes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitInterface {
    @POST("user/login ")
    suspend fun login(@Body modelLoginPost: ModelLoginPost): Response<ModelLogin>

    @GET("feelings")
    suspend fun feelings(): Response<ModelFeelings>

    @GET("quotes")
    suspend fun quotes(): Response<ModelQuotes>


}