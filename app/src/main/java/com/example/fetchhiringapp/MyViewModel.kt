package com.example.fetchhiringapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    private val _fetchData = MutableLiveData("No Data")
    val fetchData: LiveData<String> get() = _fetchData

    init {
        viewModelScope.launch {
            getItem()
        }
    }

    private suspend fun getItem() {
        _fetchData.value = RetrofitClient.fetchDataAPIService.getItems().toString()
    }
}