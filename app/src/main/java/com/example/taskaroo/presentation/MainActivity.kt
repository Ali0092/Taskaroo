package com.example.taskaroo.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.presentation.nav_component.AppsNavHost
import com.example.taskaroo.presentation.nav_component.Screens
import com.example.taskaroo.presentation.viewmodel.PrefsViewModel
import com.example.taskaroo.ui.theme.TaskarooTheme
import com.example.taskaroo.ui.theme.background
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: PrefsViewModel = getViewModel()

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isSplashLoading.value
        }

        setContent {
            TaskarooTheme {
                val dataStore: DataStoreManager = get()
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()
                systemUiController.setSystemBarsColor(
                    color = MaterialTheme.colorScheme.background
                )

                val isOnBoardingDone = dataStore.getBooleanPrefs(DataStoreManager.ON_BOARDING_DONE_KEY).collectAsState(initial = null) // Change initial value to null

                val isUserProfileCreated = dataStore.getBooleanPrefs(DataStoreManager.USER_PROFILE_DONE_KEY).collectAsState(initial = null)

                val startDestination = remember { mutableStateOf<String?>(null) }

                Log.d("checkingSideEffectConcept", "onCreate: ${isOnBoardingDone.value} ${isUserProfileCreated}")
                Log.d("checkingSideEffectConcept", "onCreate: ${isOnBoardingDone.value} ${isUserProfileCreated}")

//                LaunchedEffect(isOnBoardingDone.value, isUserProfileCreated.value) {
                    if (isOnBoardingDone.value != null && isUserProfileCreated.value != null) {
                        startDestination.value = when {
                            isOnBoardingDone.value == true && isUserProfileCreated.value == true -> Screens.MAIN.name
                            isOnBoardingDone.value == true -> Screens.USER_PROFILE.name
                            else -> Screens.ONBOARDING.name
                        }

                        // Hide splash only when decision is made
                        viewModel.setSplashLoadingStateFalse()
                    }
//                }

                if (startDestination.value == null) {
                    viewModel.setSplashLoadingStateTrue()
                } else {
                    AppsNavHost(
                        navController = navController, startDestination = startDestination.value!!
                    )
                }

            }
        }
    }
}