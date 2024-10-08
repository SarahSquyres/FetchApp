package com.example.fetchhiringapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun GroupData(selectedListId: Int, modifier: Modifier = Modifier, viewModel: MyViewModel = viewModel()) {
    Column {
        val searchText = viewModel.searchText.collectAsState(initial = "").value
        SearchBar(
            text = searchText,
            onTextChange = { text -> viewModel.onSearchTextChange(text) }
        )
        val data = viewModel.fetchData.observeAsState().value

        if (data != null) {
            val listIdItems = data.filter { (listId, _) -> selectedListId == listId }.flatMap { (_, items) -> items }
            val filteredItems = listIdItems.filter { item ->
                item.name?.contains(searchText, ignoreCase = true) ?: false
            }

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(filteredItems) { item ->
                    GroupItemCard(item = item)
                }
            }
        }
    }
}

@Composable
fun GroupItemCard(item: Item, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        modifier = modifier
            .width(300.dp)
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row {
                Text(
                    text = "List ID ",
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${item.listId}",
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "ID ",
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${item.id}",
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${item.name}",
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}