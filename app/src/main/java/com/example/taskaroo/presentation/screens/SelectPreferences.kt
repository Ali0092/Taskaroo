package com.example.taskaroo.presentation.screens

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.domain.model.PrefsModel
import com.example.taskaroo.presentation.viewmodel.UserViewModel
import com.example.taskaroo.ui.theme.cardColor
import com.example.taskaroo.ui.theme.gradientEndColor
import com.example.taskaroo.ui.theme.gradientStartColor
import com.example.taskaroo.ui.theme.liteDarkGray
import com.example.taskaroo.ui.theme.onBackground
import com.example.taskaroo.ui.theme.primaryColor
import com.example.taskaroo.ui.theme.primaryColorVariant
import com.example.taskaroo.ui.theme.textColor
import com.example.taskaroo.ui.theme.red
import org.koin.androidx.compose.get

@Composable
fun SelectPreferences(userViewModel: UserViewModel = get()) {

    var selectedPreferences = remember { mutableStateListOf("") }

    val prefsList = listOf(
        PrefsModel(R.drawable.user, stringResource(R.string.preference_personal)),
        PrefsModel(R.drawable.working,stringResource(R.string.preference_work)),
        PrefsModel(R.drawable.education,stringResource(R.string.preference_education))
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(top = 16.sdp),
            text = stringResource(R.string.preference_ScreenTitle),
            fontSize = 18.textSdp,
            fontWeight = FontWeight.SemiBold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(4.sdp))

        Text(text = stringResource(R.string.preference_ScreenSubTitle),
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Normal,
            color = primaryColorVariant
        )

        Spacer(modifier = Modifier.height(16.sdp))

        Column {
            repeat(prefsList.size) { index->
                PreferenceSingleItem(prefsList[index].icon,prefsList[index].title) { selected->

                    if (selectedPreferences.contains(selected)) {
                        selectedPreferences.remove(selected)
                    } else {
                        userViewModel.setUser(userViewModel.user.value.copy(preferences = userViewModel.user.value.preferences+selected))
                        selectedPreferences.add(selected)
                    }
                }
            }
        }

    }
}

@Composable
fun PreferenceSingleItem(icon: Int, title: String, onSelected: (String) -> Unit ) {

    var isChecked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.sdp)
            .background(Color.Transparent),
        shape = RoundedCornerShape(12.sdp),
        colors = CardDefaults.cardColors(containerColor = if(isChecked) onBackground else Color.Transparent),
        border = BorderStroke(width = 0.5.dp, color = primaryColorVariant),
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 21.sdp, horizontal = 12.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {

            Image(painter = painterResource(icon),contentDescription = null, modifier = Modifier.size(50.sdp))

            Spacer(Modifier.width(8.sdp))

            Text(text = title,
                fontSize = 16.textSdp,
                fontWeight = FontWeight.Medium,
                color = primaryColor
            )
            Spacer(Modifier.weight(1f))

            Icon(imageVector = if (isChecked) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(25.sdp).clickable{
                    isChecked = !isChecked
                    onSelected(title)
                },
                tint = if (isChecked) primaryColor else primaryColorVariant
            )
        }

    }
}