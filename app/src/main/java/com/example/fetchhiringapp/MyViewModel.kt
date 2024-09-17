package com.example.fetchhiringapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    private val _fetchData = MutableLiveData<Map<Int, List<Item>>>(emptyMap())
    val fetchData: LiveData<Map<Int, List<Item>>> get() = _fetchData

    init {
        viewModelScope.launch {
            getItem()
        }
    }

    private suspend fun getItem() {
        val items = RetrofitClient.fetchDataAPIService.getItems()
        val filteredItems = items.filter { it.name != null && it.name.isNotEmpty() }
        val sortedItems = filteredItems.sortedWith(compareBy({ it.listId }, { it.name!!.extractNumericValue() }))
        val groupedItems = sortedItems.groupBy { it.listId }
        _fetchData.value = groupedItems
    }

    private fun String.extractNumericValue(): Int {
        val regex = """\d+""".toRegex()
        val match = regex.find(this)
        return match?.value?.toIntOrNull() ?: 0
    }
}