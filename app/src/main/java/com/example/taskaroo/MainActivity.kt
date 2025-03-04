package com.example.taskaroo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.nav_component.AppsNavHost
import com.example.taskaroo.ui.theme.TaskarooTheme
import com.example.taskaroo.ui.theme.darkGray


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskarooTheme {
                AppsNavHost(navController = rememberNavController())
            }
        }
    }

}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(darkGray),
        topBar = {},
        bottomBar = {},
        content = { paddingValue->
            HomeScreen(modifier = Modifier.padding(paddingValue))
        },
        floatingActionButton = {})
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier= modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("HomeScreen", fontSize = 24.textSdp)
    }
}