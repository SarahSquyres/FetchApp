//MainActivity
package com.example.fetchhiringapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchhiringapp.ui.theme.FetchHiringAppTheme


@Composable
fun GroupTwoData(modifier: Modifier = Modifier, viewModel: MyViewModel = viewModel()) {
    val data = viewModel.fetchData.observeAsState().value

    if (data != null) {
        val listId2Items = data.filter { (listId, _) -> listId == 2 }.flatMap { (_, items) -> items }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(50.dp)
        ) {
            items(listId2Items) { item ->
                GroupTwoItemCard(item = item)
            }
        }
    }
}

@Composable
fun GroupTwoItemCard(item: Item, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Text(
                text = "List ID: ${item.listId}",
                modifier = Modifier.padding(1.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Name: ${item.name}",
                modifier = Modifier.padding(1.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "ID: ${item.id}",
                modifier = Modifier.padding(1.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

