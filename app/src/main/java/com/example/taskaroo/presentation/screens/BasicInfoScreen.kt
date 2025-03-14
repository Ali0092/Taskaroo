package com.example.taskaroo.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.presentation.viewmodel.UserViewModel
import com.example.taskaroo.ui.theme.backgroundColor
import com.example.taskaroo.ui.theme.red
import com.example.taskaroo.ui.theme.textColor
import org.koin.androidx.compose.get

@Composable
fun BasicInfoScreen(userViewModel: UserViewModel = get()) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Image(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier.size(25.sdp),
            colorFilter = ColorFilter.tint(textColor)
        )

        Text(
            modifier = Modifier.padding(top = 24.sdp),
            text = stringResource(R.string.basic_info_ScreenTitle),
            fontSize = 18.textSdp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(4.sdp))

        Text(
            text = stringResource(R.string.basic_info_ScreenSubTitle),
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Normal,
            color = textColor
        )

        Spacer(modifier = Modifier.height(24.sdp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.basic_info_ScreenInfoHeading),
                fontSize = 15.textSdp,
                color = textColor
            )

            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                    userViewModel.setUser(userViewModel.user.value.copy(firstName = it))
                },
                label = { Text(stringResource(R.string.labelFirstName)) },
                placeholder = { Text(stringResource(R.string.hintFirstName)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = red,   // Border color when focused
                    unfocusedBorderColor = textColor, // Border color when not focused
                    cursorColor = red,          // Cursor color
                    focusedLabelColor = red,
                    unfocusedLabelColor = textColor,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                    userViewModel.setUser(userViewModel.user.value.copy(lastName = it))
                },
                label = { Text(stringResource(R.string.labelLastName)) },
                placeholder = { Text(stringResource(R.string.hintLastName)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = red,   // Border color when focused
                    unfocusedBorderColor = textColor, // Border color when not focused
                    cursorColor = red,          // Cursor color
                    focusedLabelColor = red,
                    unfocusedLabelColor = textColor,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                )
            )
        }

    }
}
