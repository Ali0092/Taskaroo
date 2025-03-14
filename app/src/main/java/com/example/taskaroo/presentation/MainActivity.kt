package com.example.taskaroo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.taskaroo.presentation.nav_component.AppsNavHost
import com.example.taskaroo.presentation.nav_component.Screens
import com.example.taskaroo.presentation.viewmodel.PrefsViewModel
import com.example.taskaroo.ui.theme.TaskarooTheme
import com.example.taskaroo.ui.theme.backgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: PrefsViewModel = getViewModel()

        installSplashScreen().setKeepOnScreenCondition {

            viewModel.isOnBoardingDone.value != null
//                    || viewModel.isUserProfileCreated.value != null
        }

        setContent {
            TaskarooTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = backgroundColor
                )
//                val isOnBoardingDone by viewModel.isOnBoardingDone.collectAsState()
//                val isUserProfileCreated by viewModel.isUserProfileCreated.collectAsState()
//
//                var startDestination by remember { mutableStateOf<String?>(null) }
//
//                LaunchedEffect(isOnBoardingDone.value) {
//                    if (isOnBoardingDone.value!=null) {
//                        startDestination = if (isOnBoardingDone.value==true) { //checks if onboarding is done or not
//                            if (isUserProfileCreated.value==true) { //checks if user profile is created or not
//                                Screens.MAIN.name
//                            }else Screens.USER_PROFILE.name
//                        }  else Screens.ONBOARDING.name
//                    }
//                }
//
//                if (startDestination!=null) {
//                    AppsNavHost(navController = rememberNavController(),
//                        startDestination = startDestination!!)
//                }
//

//                val startDestination by remember(isOnBoardingDone.value, isUserProfileCreated.value) {
//                    mutableStateOf(
//                        when {
//                            isOnBoardingDone.value == true && isUserProfileCreated.value == true -> Screens.MAIN.name
//                            isOnBoardingDone.value == true -> Screens.USER_PROFILE.name
//                            else -> Screens.ONBOARDING.name
//                        }
//                    )
//                }

                val isOnBoardingDone by viewModel.isOnBoardingDone.collectAsState()
                val isUserProfileCreated by viewModel.isUserProfileCreated.collectAsState()

                val startDestination =
                    if (isOnBoardingDone != null) { //checks if onboarding is done or not
                        if (isUserProfileCreated != null) { //checks if user profile is created or not
                            Screens.MAIN.name
                        } else Screens.USER_PROFILE.name
                    } else Screens.ONBOARDING.name

                AppsNavHost(
                    navController = rememberNavController(),
                    startDestination = startDestination
                )

            }
        }
    }
}