package com.example.taskaroo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.components.DotIndicator
import com.example.taskaroo.model.PagerModel
import com.example.taskaroo.nav_component.Screens
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.red
import com.example.taskaroo.ui.theme.textColor

@Composable
fun OnBoardingScreen(navController: NavController) {

    val picturesList = listOf(
        PagerModel(
            image = R.drawable.onboarding_1,
            quote = "My Tasks used to be scattered all around. Now\n they`re in one place!",
            author = "Peter Parker."
        ),
        PagerModel(
            image = R.drawable.onboarding_2,
            quote = "Focus on being productive instead of \nbusy",
            author = "– Tim Ferriss"
        ),
        PagerModel(
            image = R.drawable.onboarding_3,
            quote = "What gets measured gets \nmanaged.",
            author = "– Peter Drucker"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val pagerState = rememberPagerState(pageCount = { 3 })
        //top title....
        Row(
            modifier = Modifier
                .padding(top = 44.sdp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(30.sdp)
                    .clip(RoundedCornerShape(5.sdp))
            )

            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.padding(start = 10.sdp),
                color = textColor,
                fontSize = 16.textSdp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        //top title
        Spacer(modifier = Modifier.height(24.sdp))

        Text(
            text = stringResource(R.string.onboarding_subtitle),
            color = textColor,
            fontSize = 24.textSdp,
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


        ElevatedButton(onClick = {
            navController.navigate(Screens.SIGNUP.name)
        },
            colors = ButtonDefaults.buttonColors(containerColor = red)
        ) {
            Text(
                text = stringResource(R.string.buttonGetStarted),
                modifier = Modifier.padding(vertical = 6.sdp, horizontal = 12.sdp),
                color = White,
                fontSize = 18.textSdp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


        //register with email
//        Card(
//            modifier = Modifier
//                .background(Color.Transparent)
//                .clickable {
//                    navController.navigate(Screens.SIGNUP.name)
//                },
//            shape = CircleShape,
//            colors = CardDefaults.cardColors(containerColor = red),
//        ) {
//            Row(
//                modifier = Modifier
//                    .wrapContentWidth()
//                    .padding(vertical = 12.sdp, horizontal = 21.sdp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center,
//
//                ) {
////                Image(
////                    imageVector =  Icons.Rounded.PlayArrow,
////                    contentDescription = null, modifier = Modifier.size(25.sdp),
////                    colorFilter = ColorFilter.tint(textColor)
////                )
//
//
//
//            }
//        }
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
                .fillMaxWidth(),
            text = quote,
            color = textColor,
            fontSize = 16.textSdp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.sdp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = author,
            color = textColor,
            fontSize = 11.textSdp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

    }
}
