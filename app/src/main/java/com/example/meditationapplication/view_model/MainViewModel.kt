package com.example.meditationapplication.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditationapplication.login.model.ModelLogin
import com.example.meditationapplication.login.model.ModelLoginPost
import com.example.meditationapplication.main.model.ModelFeelings
import com.example.meditationapplication.main.model.ModelQuotes
import com.example.meditationapplication.retrofit.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val loginResponse: MutableLiveData<Response<ModelLogin>> = MutableLiveData()
    val feelingsResponse: MutableLiveData<Response<ModelFeelings>> = MutableLiveData()
    val quotesResponse: MutableLiveData<Response<ModelQuotes>> = MutableLiveData()

    val loginTransitResponse: MutableLiveData<ModelLogin> = MutableLiveData()

    //view model to pass the value to fragment
    fun loginTransit(modelLogin: ModelLogin){
        viewModelScope.launch {
            val response = repository.loginTransit(modelLogin)
            loginTransitResponse.value = response
        }
    }

    fun login(modelLoginPost: ModelLoginPost) {
        viewModelScope.launch {
            val response = repository.login(modelLoginPost)
            loginResponse.value = response
        }
    }

    fun feelings() {
        viewModelScope.launch {
            val response = repository.feelings()
            feelingsResponse.value = response
        }
    }

    fun quotes(){
        viewModelScope.launch {
            val response = repository.quotes()
            quotesResponse.value = response
        }
    }
}