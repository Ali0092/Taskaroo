package com.example.taskaroo.nav_component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.taskaroo.ui.screens.OnBoardingScreen
import com.example.taskaroo.ui.screens.SignUpScreen

enum class Screens{
    ONBOARDING,
    SIGNUP,
    USER_PROFILE,
    MAIN
}

//this is our simple screen navigation
sealed class SimpleScreenNavigationItem(val route: String){
    object OnBoarding: SimpleScreenNavigationItem(Screens.ONBOARDING.name)
    object Signup: SimpleScreenNavigationItem(Screens.SIGNUP.name)
    object UserProfile: SimpleScreenNavigationItem(Screens.USER_PROFILE.name)
    object Main: SimpleScreenNavigationItem(Screens.MAIN.name)
}

//this is out nav_graph
@Composable
fun AppsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = SimpleScreenNavigationItem.OnBoarding.route) {


    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){

        composable(SimpleScreenNavigationItem.OnBoarding.route) {
            OnBoardingScreen()
        }

        composable(SimpleScreenNavigationItem.Signup.route) {
            SignUpScreen()
        }

        composable(SimpleScreenNavigationItem.UserProfile.route) {
            SignUpScreen()
        }

        composable(SimpleScreenNavigationItem.Main.route) {
            SignUpScreen()
        }

    }
    
}