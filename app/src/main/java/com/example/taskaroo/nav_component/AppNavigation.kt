package com.example.taskaroo.nav_component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.taskaroo.ui.screens.CreateProfile
import com.example.taskaroo.ui.screens.MainScreen
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
    object AddTask: SimpleScreenNavigationItem(Screens.ADD_TASK.name)
}

sealed class BottomNavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavigationItem(Screens.HOME.name, Icons.Rounded.Home, "Home")
    object Search : BottomNavigationItem(Screens.SEARCH.name, Icons.Rounded.Search, "Search")
    object Profile : BottomNavigationItem(Screens.PROFILE.name, Icons.Rounded.Person, "Profile")
}
//this is out nav_graph
@Composable
fun AppsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = SimpleScreenNavigationItem.Main.route) {


    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){

        composable(SimpleScreenNavigationItem.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }

        composable(SimpleScreenNavigationItem.Signup.route) {
            MainScreen()
//            SignUpScreen(navController = navController)
        }

        composable(SimpleScreenNavigationItem.UserProfile.route) {
            CreateProfile(navController = navController)
        }

        composable(SimpleScreenNavigationItem.Main.route) {
            MainScreen()
        }

    }
    
}