package com.example.taskaroo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.taskaroo.ui.screens.CreateProfile
import com.example.taskaroo.ui.theme.TaskarooTheme

data class PagerModel(val image: Int, val quote: String, val author: String)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskarooTheme {
                CreateProfile()
            }
        }
    }

}
