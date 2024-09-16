//MyViewModel
package com.example.fetchhiringapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    private val _fetchData = MutableLiveData<List<Item>>(emptyList())
    val fetchData: LiveData<List<Item>> get() = _fetchData

    init {
        viewModelScope.launch {
            getItem()
        }
    }

    private suspend fun getItem() {
        val items = RetrofitClient.fetchDataAPIService.getItems()
        val filteredItems = items.filter { it.name != null && it.name.isNotEmpty() }
        val sortedItems = filteredItems.sortedWith(compareBy({ it.listId }, { it.name }))
        _fetchData.value = sortedItems
    }
}