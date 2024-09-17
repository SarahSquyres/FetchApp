//MainActivity
package com.example.fetchhiringapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    color = Color(0xFF222222)
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.mainScreen, builder = {
                        composable(Routes.mainScreen){
                            ListButtons(navController)
                        }
                        composable(Routes.listOne){
                            GroupOneData()
                        }
                        composable(Routes.listTwo){
                            GroupTwoData()
                        }
                        composable(Routes.listThree){
                            GroupThreeData()
                        }
                        composable(Routes.listFour){
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
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Welcome to MyListApp",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier
                .padding(1.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_list_alt_24),
            contentDescription = "List Icon",
            modifier = Modifier.size(200.dp),
            tint = Color.LightGray
        )
        Text(text = "Select the list you would like to view:",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = Color.White,
            modifier = Modifier
                .padding(1.dp)
            )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.listOne)
                },
                modifier = Modifier
                    .size(150.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF9FBDC6)),
            ) {
                Text(text = "View List ID 1",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
            Button(
                onClick = {
                    navController.navigate(Routes.listTwo)
                },
                modifier = Modifier
                    .size(150.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF9FBDC6)),
            ) {
                Text(text = "View List ID 2",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.listThree)
                },
                modifier = Modifier
                    .size(150.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF9FBDC6)),
            ) {
                Text(text = "View List ID 3",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
            Button(
                onClick = {
                    navController.navigate(Routes.listFour)
                },
                modifier = Modifier
                    .size(150.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF9FBDC6))
            ) {
                Text(
                    text = "View List ID 4",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListButtonsPreview() {
    FetchHiringAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            ListButtons(navController)
        }
    }
}