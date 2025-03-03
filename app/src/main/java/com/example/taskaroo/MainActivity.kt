package com.example.taskaroo

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.util.lerp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskaroo.ui.theme.TaskarooTheme
import com.example.taskaroo.ui.theme.darkGray
import com.example.taskaroo.ui.theme.lightGray
import com.example.taskaroo.ui.theme.red
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskarooTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OnBoardingScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskarooTheme {
        OnBoardingScreen(Modifier.fillMaxSize().background(darkGray))
    }
}

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {

    val picturesList = listOf(
        R.drawable.onboarding_1,
        R.drawable.onboarding_2,
        R.drawable.onboarding_3
    )

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        val pagerState = rememberPagerState(pageCount = { 3 })

        Row(modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(R.drawable.app_icon),
                contentDescription = null, modifier = Modifier.size(30.dp))

            Text(text = stringResource(R.string.app_name),
                modifier = Modifier.padding(start = 10.dp),
                color = red,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        HorizontalPager( state =  pagerState) { page->
            Image(painter = painterResource(picturesList[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(350.dp).padding( top = 42.dp),
                contentScale = ContentScale.Crop
            )
        }
        DotIndicator(3,pagerState.currentPage)

        Card(
            modifier = Modifier.fillMaxWidth()
                .background(Color.Transparent)
                .padding(24.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(width = 0.6.dp, color = Color.White),
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(top = 12.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {
                Image(
                    imageVector =  Icons.Default.MailOutline,
                    contentDescription = null, modifier = Modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(lightGray)
                    )

                Text(text = stringResource(R.string.continue_with_email),
                    modifier = Modifier.padding(start = 10.dp),
                    color = lightGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Composable
fun DotIndicator(
    pageCount: Int, currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size( 8.dp)
                    .clip(CircleShape)
                    .background(if (index == currentPage) red else Color.Gray.copy(alpha = 0.5f))
            )
        }
    }
}