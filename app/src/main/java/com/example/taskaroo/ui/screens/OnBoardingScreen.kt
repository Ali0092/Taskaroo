package com.example.taskaroo.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.components.DotIndicator
import com.example.taskaroo.model.PagerModel
import com.example.taskaroo.nav_component.Screens
import com.example.taskaroo.ui.theme.darkGray
import com.example.taskaroo.ui.theme.lightGray
import com.example.taskaroo.ui.theme.red

@Composable
fun OnBoardingScreen(navController: NavController) {

    val picturesList = listOf(
        PagerModel(image = R.drawable.onboarding_1, quote = "My Tasks used to be scattered all around. Now\n they`re in one place!", author = "Peter Parker."),
        PagerModel(image = R.drawable.onboarding_2, quote = "Focus on being productive instead of \nbusy", author = "– Tim Ferriss"),
        PagerModel(image = R.drawable.onboarding_3, quote = "What gets measured gets \nmanaged.", author = "– Peter Drucker")
    )

    Column(
        modifier = Modifier.fillMaxSize().background(darkGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val pagerState = rememberPagerState(pageCount = { 3 })
        //top title....
        Row(modifier = Modifier
            .padding(top = 44.sdp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(R.drawable.app_icon),
                contentDescription = null, modifier = Modifier.size(30.sdp).clip(RoundedCornerShape(5.sdp)))

            Text(text = stringResource(R.string.app_name),
                modifier = Modifier.padding(start = 10.sdp),
                color = red,
                fontSize = 24.textSdp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        //Imaged....
        HorizontalPager( state =  pagerState) { page->
            PagerView(picturesList[page].image, picturesList[page].quote, picturesList[page].author)
        }
        //Dots
        DotIndicator(3,pagerState.currentPage)

        //register with email
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(32.sdp)
                .clickable{
                    navController.navigate(Screens.SIGNUP.name)
                },
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(width = 0.3.dp, color = Color.White),
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.sdp, bottom = 12.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {
                Image(
                    imageVector =  Icons.Default.MailOutline,
                    contentDescription = null, modifier = Modifier.size(25.sdp),
                    colorFilter = ColorFilter.tint(lightGray)
                )

                Text(text = stringResource(R.string.continue_with_email),
                    modifier = Modifier.padding(start = 8.sdp),
                    color = lightGray,
                    fontSize = 13.textSdp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        //bottom text
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 24.sdp)) {
            Text(
                text = "By continuing with our services above, you agree to Taskaroo's ",
                modifier = Modifier.fillMaxWidth(),
                color = lightGray,
                fontSize = 10.textSdp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Terms of Service and Privacy Policy",
                modifier = Modifier.fillMaxWidth(),
                color = lightGray,
                fontSize = 10.textSdp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }


    }
}

@Composable
fun PagerView( image: Int, quote: String, author: String) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Image(painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(380.sdp).padding( top = 54.sdp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.sdp))

        Text(
            modifier = Modifier.padding(start = 24.sdp, end = 24.sdp).fillMaxWidth(),
            text = quote,
            color = lightGray,
            fontSize = 16.textSdp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.sdp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = author,
            color = lightGray,
            fontSize = 11.textSdp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

    }
}
