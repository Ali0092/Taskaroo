package com.example.taskaroo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskaroo.nav_component.BottomNavigationItem
import com.example.taskaroo.ui.theme.red
import com.example.taskaroo.ui.theme.textColor

@Composable
fun DotIndicator(
    pageCount: Int, currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (index == currentPage) red else Color.Gray.copy(alpha = 0.5f))
            )
        }
    }
}



@Composable
fun BottomNavigationBar(navController: NavController) {

    val screens = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Profile
    )
    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = textColor,
    ) {
        screens.forEach { screen->
            val isSelected = screen.route == currentBackStackEntry.value?.destination?.route
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title,)},
                label = { Text(screen.title) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }

}

