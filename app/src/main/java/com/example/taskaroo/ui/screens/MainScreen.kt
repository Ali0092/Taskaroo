package com.example.taskaroo.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.cardColor
import com.example.taskaroo.ui.theme.darkGray
import com.example.taskaroo.ui.theme.red
import com.example.taskaroo.ui.theme.textColor

@Composable
fun MainScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        //top app bar, name, total tasks and profile picture
        TopBar()
        Spacer(Modifier.height(16.sdp))
        SearchBar()
        Spacer(Modifier.height(24.sdp))
        SectionCategories()
    }
}

@Composable
fun SectionCategories() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 21.sdp)) {
        Text(
            text = "Categories",
            color = textColor,
            fontSize = 18.textSdp,
            fontWeight = FontWeight.Bold,
        )
    }

}

@Composable
fun ItemCategoriesSection() {
    Card(modifier = Modifier.size(45.sdp),
        shape = RoundedCornerShape(12.sdp),
        colors = CardDefaults.cardColors(containerColor =Color.Transparent),
        border = BorderStroke(width = 0.4.dp, color = textColor)) {
        Column {
            Text(
                text = "Mobile App",
                color = red,
                fontSize = 17.textSdp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "10 Tasks",
                modifier = Modifier.padding(top = 3.sdp),
                color = textColor,
                fontSize = 14.textSdp,
                fontWeight = FontWeight.Normal,
            )
            Image(
                painter = painterResource(R.drawable.onboarding_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}

@Composable
fun SearchBar() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.sdp, horizontal = 16.sdp)
            .background(Color.Transparent)
            .clickable {},
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = cardColor),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.sdp, horizontal = 21.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            Image(
                imageVector =  Icons.Rounded.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(25.sdp)
                    .alpha(0.6f),
                colorFilter = ColorFilter.tint(textColor)
            )

            Text(
                text = "search ",
                modifier = Modifier
                    .padding(start = 3.sdp)
                    .alpha(0.6f),
                color = textColor,
                fontSize = 16.textSdp,
                fontWeight = FontWeight.Normal,
            )
        }
    }

}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth()
            .padding(top = 60.sdp, start = 21.sdp, end = 21.sdp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Hi Falana Dhimkana",
                color = textColor,
                fontSize = 21.textSdp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "10 Pending Tasks",
                modifier = Modifier.padding(top = 3.sdp),
                color = red,
                fontSize = 14.textSdp,
                fontWeight = FontWeight.Normal,
            )
        }


        Card(modifier = Modifier.size(45.sdp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor =Color.Transparent),
            border = BorderStroke(width = 0.4.dp, color = textColor)) {
            Image(
                painter = painterResource(R.drawable.onboarding_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}