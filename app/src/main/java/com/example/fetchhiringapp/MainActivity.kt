//MainActivity
package com.example.fetchhiringapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fetchhiringapp.ui.theme.FetchHiringAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchHiringAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main_Activity", builder = {
                        composable("main_Activity"){
                            ListButtons(navController)
                        }
                        composable("list_One"){
                            GroupOneData()
                        }
                        composable("list_Two"){
                            GroupTwoData()
                        }
                        composable("list_Three"){
                            GroupThreeData()
                        }
                        composable("list_Four"){
                            GroupFourData()
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun ListButtons(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Button(onClick = {
            navController.navigate("list_One")
        }) {
            Text(text = "View List ID 1")
        }
        Button(onClick = {
            navController.navigate("list_Two")
        }) {
            Text(text = "View List ID 2")
        }
        Button(onClick = {
            navController.navigate("list_Three")
        }) {
            Text(text = "View List ID 3")
        }
        Button(onClick = {
            navController.navigate("list_Four")
        }) {
            Text(text = "View List ID 4")
        }
    }

}
@Composable
fun HiringData(modifier: Modifier = Modifier, viewModel: MyViewModel = viewModel()) {
    val data = viewModel.fetchData.observeAsState().value

    if (data != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(50.dp)
        ) {
            data.forEach { (listId, items) ->
                Text(
                    text = "List ID: $listId",
                    modifier = Modifier.padding(1.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                LazyColumn {
                    items(items) { item ->
                        ItemCard(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(item: Item, modifier: Modifier = Modifier) {
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

//@Preview(showBackground = true)
//@Composable
//fun HiringDataPreview() {
//    FetchHiringAppTheme {
//        ListButtons(navController)
//    }
//}