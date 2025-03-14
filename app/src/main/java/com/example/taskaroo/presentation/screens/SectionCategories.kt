package com.example.taskaroo.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.domain.model.CategoryModel
import com.example.taskaroo.ui.theme.cardColor
import com.example.taskaroo.ui.theme.textColor

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
            .padding(horizontal = 24.sdp)
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
            .padding(end = 16.sdp)
            .clip(RoundedCornerShape(20.sdp))
            .background(cardColor)
    ) {
        Column(
            modifier = Modifier.padding(start = 16.sdp, top = 16.sdp)
        ) {

            Text(
                text = title,
                color = textColor,
                fontSize = 17.textSdp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "${totalTasks} Pending Tasks",
                modifier = Modifier
                    .padding(top = 1.sdp)
                    .alpha(0.7f),
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
