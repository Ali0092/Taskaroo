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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.example.taskaroo.R
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.nav_component.Screens
import com.example.taskaroo.ui.theme.darkGray
import com.example.taskaroo.ui.theme.textColor
import com.example.taskaroo.ui.theme.red

@Composable
fun SignUpScreen(navController: NavController) {

    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().background(darkGray).padding(start = 12.sdp, end = 12.sdp, top = 44.sdp)) {

        Image(imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier.size(25.sdp),
            colorFilter = ColorFilter.tint(textColor)
        )

        Text(
            modifier = Modifier.padding(top = 24.sdp),
            text = "Sing up",
            fontSize = 24.textSdp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(4.sdp))

        Text(text = "Add your email and password.",
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Normal,
            color = textColor
        )

        Spacer(modifier = Modifier.height(24.sdp))

        OutlinedTextField(
            value = emailText,
            onValueChange = { emailText = it },
            label = { Text("Email") },
            placeholder = { Text("Enter your Email...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors =  OutlinedTextFieldDefaults.colors(
                focusedBorderColor = red,   // Border color when focused
                unfocusedBorderColor = textColor, // Border color when not focused
                cursorColor = red,          // Cursor color
                focusedLabelColor = red,
                unfocusedLabelColor = textColor,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.sdp))

        OutlinedTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            label = { Text("Password") },
            placeholder = { Text("Enter password...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors =  OutlinedTextFieldDefaults.colors(
                focusedBorderColor = red,   // Border color when focused
                unfocusedBorderColor = textColor, // Border color when not focused
                cursorColor = red,          // Cursor color
                focusedLabelColor = red,
                unfocusedLabelColor = textColor,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (isPasswordVisible) R.drawable.visibilty_icon else R.drawable.visibility_off_icon
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(painter = painterResource(icon), contentDescription = null, modifier = Modifier.size(25.sdp))
                }
            }
        )

        Spacer(modifier = Modifier.height(21.sdp))

        Button(
            onClick = {
                navController.navigate(Screens.USER_PROFILE.name)
            },
            enabled = true,
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = red)
        ) {
            Text("Sign up", modifier = Modifier.padding(vertical = 8.sdp), color = textColor)
        }


    }
}