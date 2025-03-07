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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.model.CategoryModel
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.cardColor
import com.example.taskaroo.ui.theme.red
import com.example.taskaroo.ui.theme.textColor

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        //top app bar, name, total tasks and profile picture
        TopBar()
        Spacer(Modifier.height(16.sdp))

        SearchBar()
        Spacer(Modifier.height(24.sdp))

        SectionCategories()
        Spacer(Modifier.height(24.sdp))

        SectionTasks()
    }
}

@Composable
fun SectionTasks() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.sdp)
    ) {
        Text(
            text = "Ongoing Tasks",
            color = textColor,s
            fontSize = 18.textSdp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.sdp))
        LazyColumn {
            items(5) {
                ItemTaskSection()
            }
        }


    }
}

@Composable
fun ItemTaskSection() {

    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(150.sdp)
            .clickable {

            },
        shape = RoundedCornerShape(20.sdp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        border = BorderStroke(width = 0.4.dp, color = textColor)
    ) {
        Column(modifier = Modifier.padding(16.sdp).fillMaxSize()) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Current IDGAF Tasks",
                    color = textColor,
                    fontSize = 17.textSdp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "1 day", color = red, fontSize = 10.textSdp)

            }

            Spacer(modifier = Modifier.height(12.sdp))
            Text(
                text = "This task is all about the IDGAF, The IDGAF means not giving fuck about people who are stupid, dont give value to you, to your emotions, time, effort",
                color = textColor,
                fontSize = 12.textSdp,
                fontWeight = FontWeight.Bold
            )
        }

    }

}

@Composable
fun SectionCategories() {

    val categoriesList = listOf(
        CategoryModel(title = "Mobile App", totalTasks = 10, icon = R.drawable.cat_android),
        CategoryModel(title = "Web App", totalTasks = 10, icon = R.drawable.cat_web),
        CategoryModel(title = "Personal ", totalTasks = 10, icon = R.drawable.cat_personal),
        CategoryModel(title = "Travel", totalTasks = 10, icon = R.drawable.cat_travel),
        CategoryModel(title = "Professional", totalTasks = 10, icon = R.drawable.cat_work),
        CategoryModel(title = "Art", totalTasks = 10, icon = R.drawable.car_art),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.sdp)
    ) {
        Text(
            text = "Categories",
            color = textColor,
            fontSize = 18.textSdp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.sdp))

        LazyRow {
            items(categoriesList) { category ->
                ItemCategoriesSection(
                    title = category.title, totalTasks = category.totalTasks, icon = category.icon
                )
            }
        }

    }

}

@Composable
fun ItemCategoriesSection(title: String, totalTasks: Int, icon: Int) {

    Box(
        modifier = Modifier
            .width(150.sdp)
            .height(160.sdp)
            .padding(end = 12.sdp)
            .clip(RoundedCornerShape(20.sdp))
            .background(cardColor)
    ) {
        Column(
            modifier = Modifier.padding(start = 16.sdp, top = 16.sdp)
        ) {

            Text(
                text = title,
                color = red,
                fontSize = 17.textSdp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "${totalTasks} Pending Tasks",
                modifier = Modifier.padding(top = 2.sdp),
                color = textColor,
                fontSize = 13.textSdp,
                fontWeight = FontWeight.Normal,
            )

            Spacer(modifier = Modifier.height(8.sdp))

        }

        Image(
            painter = painterResource(icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.sdp)
                .offset(x = 1.sdp, y = 5.sdp)
                .align(Alignment.BottomEnd)
        )

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
            modifier = Modifier.padding(vertical = 12.sdp, horizontal = 21.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            Image(
                imageVector = Icons.Rounded.Search,
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


        Card(
            modifier = Modifier.size(45.sdp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(width = 0.4.dp, color = textColor)
        ) {
            Image(
                painter = painterResource(R.drawable.onboarding_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}