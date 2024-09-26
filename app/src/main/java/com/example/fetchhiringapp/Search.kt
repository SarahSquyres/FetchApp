package com.example.fetchhiringapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Search(viewModel: MyViewModel = viewModel()) {
    Column {
        val searchText = viewModel.searchText.collectAsState(initial = "").value
        SearchBar(
            text = searchText,
            onTextChange = { text -> viewModel.onSearchTextChange(text) }
        )
            SearchData(viewModel.fetchData.observeAsState().value, viewModel = viewModel)
    }
}

@Composable
fun SearchBar(text: String, onTextChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 16.dp, end = 16.dp, bottom = 5.dp)
            .background(color = Color.LightGray),
        label = { Text("Search by Item") }
    )
}

@Composable
fun SearchData(data: Map<Int, List<Item>>?, modifier: Modifier = Modifier, viewModel: MyViewModel = viewModel()) {
    val searchText = viewModel.searchText.collectAsState(initial = "").value
    if (data != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            data.forEach { group ->
                val filteredItems = group.value.filter { item ->
                    item.name?.contains(searchText, ignoreCase = true) ?: false ||
                            item.name?.contains(searchText, ignoreCase = true) ?: false
                }
                if (filteredItems.isNotEmpty()) {
                    item {
                        Text(
                            text = "List ${group.key}",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.LightGray,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    filteredItems.forEach { item ->
                        item {
                            GroupItemCard(item = item)
                        }
                    }
                }
            }
        }
    }
}