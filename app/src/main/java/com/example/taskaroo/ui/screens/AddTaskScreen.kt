package com.example.taskaroo.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.ui.theme.Purple40
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.blue
import com.example.taskaroo.ui.theme.cardColor
import com.example.taskaroo.ui.theme.green
import com.example.taskaroo.ui.theme.orange
import com.example.taskaroo.ui.theme.red
import com.example.taskaroo.ui.theme.textColor

@Composable
fun AddTaskScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .padding(top = 44.sdp, start = 24.sdp, end = 24.sdp)
        ) {

            //top row
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier.size(25.sdp),
                    tint = textColor
                )

                Text(
                    text = "Add Task",
                    fontSize = 16.textSdp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .offset(x = (-15).sdp),
                )

            }



            Spacer(modifier = Modifier.height(24.sdp))
            //time and priority
            Text(
                text = "Pick Priority & Time",
                fontSize = 18.textSdp,
                color = textColor,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.sdp))

            //cat and priority
            Row {
                ItemTimeAndPriority(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.icon_priority,
                    title = "Priority",
                    subTitle = "Normal",
                    tint = blue,
                )
                Spacer(modifier = Modifier.width(12.sdp))
                ItemTimeAndPriority(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.icon_category,
                    title = "Category",
                    subTitle = "Default",
                    tint = orange,
                )

            }
            Spacer(modifier = Modifier.height(12.sdp))
            Row {
                ItemTimeAndPriority(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.icon_clock,
                    title = "Start Time",
                    subTitle = "12-12-2024",
                    tint = green,
                )
                Spacer(modifier = Modifier.width(12.sdp))
                //timeEnd
                ItemTimeAndPriority(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.icon_clock,
                    title = "End Time",
                    subTitle = "12-12-2025",
                    tint = Purple40,
                )
            }
            Spacer(modifier = Modifier.height(24.sdp))

            Text(
                text = "Task Details",
                fontSize = 18.textSdp,
                color = textColor,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(8.sdp))
            CustomTextField(placeHolderText = "Enter Title")
            CustomTextField(placeHolderText = "Description")
        }
    }

}

@Composable
fun CustomTextField( placeHolderText: String) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        placeholder = { Text(placeHolderText) },
        onValueChange = { text = it },
        modifier = Modifier.fillMaxWidth().padding(0.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            cursorColor = red,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = red,
            unfocusedLabelColor = textColor,
        ),
        shape = RectangleShape,
        textStyle = TextStyle(fontSize = 20.textSdp),
    )
}

@Composable
fun ItemTimeAndPriority(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    subTitle: String,
    tint: Color
) {
    Card(
        modifier = modifier
            .background(Color.Transparent)
            .height(70.sdp)
            .clickable {

            },
        shape = RoundedCornerShape(20.sdp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        border = BorderStroke(width = 0.7.dp, color = Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(25.sdp),
                colorFilter = ColorFilter.tint(tint)
            )
            Spacer(modifier = Modifier.width(6.sdp))
            Column {
                Text(
                    text = title,
                    fontSize = 12.textSdp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.alpha(0.7f)
                )
                Spacer(modifier = Modifier.width(2.sdp))
                Text(
                    text = subTitle,
                    fontSize = 14.textSdp,
                    color = textColor
                )
            }
        }
    }
}