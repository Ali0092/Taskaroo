package com.example.taskaroo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.taskaroo.common.sdp
import com.example.taskaroo.ui.theme.onBackground
import com.example.taskaroo.ui.theme.primaryColor
import com.example.taskaroo.ui.theme.red

@Composable
fun IconSurface(icon: ImageVector, getAddButtonClick:()-> Unit) {
    Surface(
        modifier = Modifier
            .clickable {
                getAddButtonClick()
            },
        color = onBackground,
        shape = RoundedCornerShape(12.sdp),
        shadowElevation = 1.sdp
    ) {
        Icon(
            modifier = Modifier
                .padding(12.sdp)
                .size(25.sdp),
            imageVector = icon, contentDescription = null, tint = primaryColor
        )
    }
}

@Composable
fun DotIndicator(
    pageCount: Int, currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (index == currentPage) red else Color.Gray.copy(alpha = 0.5f))
            )
        }
    }
}
