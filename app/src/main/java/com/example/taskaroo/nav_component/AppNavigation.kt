package com.example.taskaroo.nav_component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.taskaroo.MainScreen
import com.example.taskaroo.ui.screens.CreateProfile
import com.example.taskaroo.ui.screens.OnBoardingScreen
import com.example.taskaroo.ui.screens.SignUpScreen

enum class Screens{
    ONBOARDING,
    SIGNUP,
    USER_PROFILE,
    MAIN,
    HOME,
    SEARCH,
    PROFILE,
    ADD_TASK,
}

//this is our simple screen navigation
sealed class SimpleScreenNavigationItem(val route: String){
    object OnBoarding: SimpleScreenNavigationItem(Screens.ONBOARDING.name)
    object Signup: SimpleScreenNavigationItem(Screens.SIGNUP.name)
    object UserProfile: SimpleScreenNavigationItem(Screens.USER_PROFILE.name)
    object Main: SimpleScreenNavigationItem(Screens.MAIN.name)
}

sealed class BottomNavigationItem(val route: String, val icon: Int, val title: String) {

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
            OnBoardingScreen(navController = navController)
        }

        composable(SimpleScreenNavigationItem.Signup.route) {
            SignUpScreen(navController = navController)
        }

        composable(SimpleScreenNavigationItem.UserProfile.route) {
            CreateProfile(navController = navController)
        }

        composable(SimpleScreenNavigationItem.Main.route) {
            MainScreen()
        }

    }
    
}