package com.example.taskaroo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.taskaroo.nav_component.AppsNavHost
import com.example.taskaroo.ui.theme.TaskarooTheme

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
