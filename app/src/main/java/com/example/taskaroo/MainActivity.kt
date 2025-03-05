package com.example.taskaroo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.nav_component.AppsNavHost
import com.example.taskaroo.nav_component.BottomNavigationItem
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

    val navController = rememberNavController()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(darkGray),
        topBar = {},
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = { paddingValue->
            Box(modifier = Modifier.fillMaxSize()) {

                NavHost(navController = navController, BottomNavigationItem.Home.route, modifier = Modifier.padding(paddingValue)){
                    composable(BottomNavigationItem.Home.route) {
                        HomeScreen()
                    }

                    composable(BottomNavigationItem.Search.route) {
                        SearchScreen()
                    }

                    composable(BottomNavigationItem.Profile.route) {
                        ProfileScreen()
                    }

                }
            }
        },
        floatingActionButton = {}
    )
}


@Composable
fun BottomNavigationBar(navController: NavController) {

    val screens = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Profile
    )
    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {
        screens.forEach { screen->
            val isSelected = screen.route == currentBackStackEntry.value?.destination?.route
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title)},
                label = { Text(screen.title) },
                selected = isSelected,
                onClick = {
                    Log.d("sdfsdfasdfsdafsdaf", "BottomNavigationBar: ${screen.route}")
                    navController.navigate(screen.route)
                }
            )
        }
    }

}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier= modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("HomeScreen", fontSize = 24.textSdp)
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Column(modifier= modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("SearchScreen", fontSize = 24.textSdp)
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier= modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("ProfileScreen", fontSize = 24.textSdp)
    }
}