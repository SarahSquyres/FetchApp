package com.example.fetchhiringapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
                    HiringData()
                }
            }
        }
    }
}

@Composable
fun HiringData(modifier: Modifier = Modifier, viewModel: MyViewModel = viewModel()) {
    val data = viewModel.fetchData.observeAsState().value

    if (data != null) {
        Text(
            text = data,
            modifier = modifier
                .padding(18.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HiringDataPreview() {
    FetchHiringAppTheme {
        HiringData()
    }
}