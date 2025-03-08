package com.example.taskaroo.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskaroo.common.sdp
import com.example.taskaroo.presentation.components.DotIndicator
import com.example.taskaroo.nav_component.Screens
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.textColor
import com.example.taskaroo.ui.theme.red
import kotlinx.coroutines.launch

@Composable
fun CreateProfile(navController: NavController) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val animationScope = rememberCoroutineScope()
    var buttonText = remember { mutableStateOf("Continue") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(start = 12.sdp, end = 12.sdp, top = 44.sdp)
    ) {

        HorizontalPager(pagerState, modifier = Modifier.weight(1f)) { page->
            if (page==0){
                SelectPreferences()
            }else {
                SelectPicture()
            }
        }

        DotIndicator(2, pagerState.currentPage)

        Spacer(modifier = Modifier.height(8.sdp))

        Button(
            onClick = {
                animationScope.launch {
                    if (pagerState.currentPage==0){
                        buttonText.value = "Get Started !"
                        pagerState.animateScrollToPage(1)
                    }else {
                        navController.navigate(Screens.MAIN.name)
                    }
                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.sdp)
        ) {
            Text(buttonText.value, modifier = Modifier.padding(vertical = 8.sdp), color = textColor)
        }

    }

}
