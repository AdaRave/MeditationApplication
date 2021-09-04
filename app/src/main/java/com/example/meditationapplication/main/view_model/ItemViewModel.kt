package com.example.meditationapplication.main.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.meditationapplication.login.model.ModelLogin

class ItemViewModel : ViewModel() {
    private val mutableSelectedItem = MediatorLiveData<ModelLogin>()
    val selectedItem: MediatorLiveData<ModelLogin> get() = mutableSelectedItem

    fun selectItem(item: ModelLogin){
        mutableSelectedItem.value = item
    }
}