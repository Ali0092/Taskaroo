package com.example.taskaroo.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.nav_component.AppsNavHost
import com.example.taskaroo.nav_component.Screens
import com.example.taskaroo.ui.theme.TaskarooTheme
import com.example.taskaroo.ui.theme.backgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskarooTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = backgroundColor
                )
                val dataStoreManage: DataStoreManager = get()
                val isOnBoardingDone = dataStoreManage.getBooleanPrefs(DataStoreManager.ON_BOARDING_DONE_KEY).collectAsState(initial = null)
                val isUserProfileCreated = dataStoreManage.getBooleanPrefs(DataStoreManager.USER_PROFILE_DONE_KEY).collectAsState(initial = null)

                var startDestination by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(isOnBoardingDone.value) {
                    if (isOnBoardingDone.value!=null) {
                        startDestination = if (isOnBoardingDone.value==true) { //checks if onboarding is done or not
                            if (isUserProfileCreated.value==true) { //checks if user profile is created or not
                                Screens.MAIN.name
                            }else Screens.USER_PROFILE.name
                        }  else Screens.ONBOARDING.name
                    }
                }

                if (startDestination!=null) {
                    AppsNavHost(navController = rememberNavController(),
                        startDestination = startDestination!!)
                }

            }
        }
    }

}