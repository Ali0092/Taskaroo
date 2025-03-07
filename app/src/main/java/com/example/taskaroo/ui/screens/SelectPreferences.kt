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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.ui.theme.textColor
import com.example.taskaroo.ui.theme.liteDarkGray
import com.example.taskaroo.ui.theme.red

@Composable
fun SelectPreferences() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(top = 24.sdp),
            text = "How do you plan to user Taskaroo?",
            fontSize = 18.textSdp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(4.sdp))

        Text(text = "Choose that apply.",
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Normal,
            color = textColor
        )

        Spacer(modifier = Modifier.height(24.sdp))

        Column {
            PreferenceSingleItem(R.drawable.user,"Personal")
            Spacer(modifier = Modifier.height(12.sdp))
            PreferenceSingleItem(R.drawable.working, "Work")
            Spacer(modifier = Modifier.height(12.sdp))
            PreferenceSingleItem(R.drawable.education, "Education")
        }

    }
}

@Composable
fun PreferenceSingleItem(icon: Int, title: String) {

    var isChecked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(5.sdp),
        colors = CardDefaults.cardColors(containerColor = if(isChecked) liteDarkGray else Color.Transparent),
        border = BorderStroke(width = 0.3.dp, color = Color.White),
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 21.sdp, horizontal = 12.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            Image(painter = painterResource(icon),contentDescription = null, modifier = Modifier.size(50.sdp))

            Spacer(Modifier.width(4.sdp))

            Text(text = title,
                fontSize = 14.textSdp,
                fontWeight = FontWeight.Normal,
                color = textColor
            )
            Spacer(Modifier.weight(1f))

            Icon(imageVector = if (isChecked) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle, contentDescription = null, modifier = Modifier.size(25.sdp).clickable{ isChecked = !isChecked }, tint = if (isChecked) red else textColor)
        }

    }
}