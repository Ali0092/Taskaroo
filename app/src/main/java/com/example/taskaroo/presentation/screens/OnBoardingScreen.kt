package com.example.taskaroo.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.data.datastore.DataStoreManager
import com.example.taskaroo.domain.model.PagerModel
import com.example.taskaroo.presentation.components.DotIndicator
import com.example.taskaroo.presentation.components.GradientButton
import com.example.taskaroo.presentation.nav_component.Screens
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun OnBoardingScreen(navController: NavController, dataStoreManager: DataStoreManager = get()) {

    val coroutineScope = rememberCoroutineScope()

    val picturesList = listOf(
        PagerModel(
            image = R.drawable.onboarding_1,
            quote = stringResource(R.string.onboarding_qoute1),
            author = stringResource(R.string.onboarding_qoute1_author)
        ), PagerModel(
            image = R.drawable.onboarding_2,
            quote = stringResource(R.string.onboarding_qoute2),
            author = stringResource(R.string.onboarding_qoute2_author)
        ), PagerModel(
            image = R.drawable.onboarding_3,
            quote = stringResource(R.string.onboarding_qoute3),
            author = stringResource(R.string.onboarding_qoute3_author)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val pagerState = rememberPagerState(pageCount = { 3 })

        //top title
        Spacer(modifier = Modifier.height(44.sdp))

        Text(
            text = stringResource(R.string.onboarding_subtitle),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.textSdp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 2,
            lineHeight = 28.textSdp
        )

        //Imaged....
        HorizontalPager(state = pagerState) { page ->
            PagerView(picturesList[page].image, picturesList[page].quote, picturesList[page].author)
        }
        //Dots
        DotIndicator(3, pagerState.currentPage)

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            text = stringResource(R.string.buttonGetStarted),
            modifier = Modifier.padding(vertical = 4.sdp, horizontal = 12.sdp)
        ) {
            coroutineScope.launch {
                dataStoreManager.saveBooleanPrefs(DataStoreManager.ON_BOARDING_DONE_KEY, true)
            }
            navController.navigate(Screens.USER_PROFILE.name)
        }
        Spacer(modifier = Modifier.weight(1f))

    }
}

@Composable
fun PagerView(image: Int, quote: String, author: String) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(380.sdp)
                .padding(top = 28.sdp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.sdp))

        Text(
            modifier = Modifier
                .padding(start = 24.sdp, end = 24.sdp)
                .fillMaxWidth()
                .alpha(0.6f),
            text = quote,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.textSdp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.sdp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = author,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 11.textSdp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}
