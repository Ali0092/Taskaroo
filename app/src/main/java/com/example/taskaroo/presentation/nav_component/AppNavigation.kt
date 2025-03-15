package com.example.taskaroo.presentation.nav_component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskaroo.presentation.screens.AddTaskScreen
import com.example.taskaroo.presentation.screens.CreateProfile
import com.example.taskaroo.presentation.screens.MainScreen
import com.example.taskaroo.presentation.screens.OnBoardingScreen
import org.koin.androidx.compose.koinViewModel

enum class Screens {
    ONBOARDING, USER_PROFILE, MAIN, SPLASH, ADD_TASK,
}

sealed class SimpleScreenNavigationItem(val route: String) {
    object OnBoarding : SimpleScreenNavigationItem(Screens.ONBOARDING.name)
    object UserProfile : SimpleScreenNavigationItem(Screens.USER_PROFILE.name)
    object Main : SimpleScreenNavigationItem(Screens.MAIN.name)
    object AddTask : SimpleScreenNavigationItem(Screens.ADD_TASK.name)
}

//this is out nav_graph
@Composable
fun AppsNavHost(
    modifier: Modifier = Modifier, navController: NavHostController, startDestination: String
) {

    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {

        composable(SimpleScreenNavigationItem.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }

        composable(SimpleScreenNavigationItem.UserProfile.route) {
            CreateProfile(navController = navController)
        }

        composable(SimpleScreenNavigationItem.Main.route) {
            MainScreen(navController)
        }


        composable(
            route = "${SimpleScreenNavigationItem.AddTask.route}/{flag}",
            arguments = listOf(navArgument("flag") { type = NavType.IntType })) { backStackEntry ->
            val flag = backStackEntry.arguments?.getInt("flag") ?: 0
            AddTaskScreen(navController, flag = flag)
        }

    }

}