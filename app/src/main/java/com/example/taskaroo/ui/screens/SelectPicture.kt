package com.example.taskaroo.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taskaroo.common.sdp
import com.example.taskaroo.common.textSdp
import com.example.taskaroo.ui.theme.darkGray
import com.example.taskaroo.ui.theme.lightGray
import com.example.taskaroo.ui.theme.red

@Composable
fun SelectPicture() {

    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkGray)
    ) {

        Image(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier.size(25.sdp),
            colorFilter = ColorFilter.tint(lightGray)
        )

        Text(
            modifier = Modifier.padding(top = 24.sdp),
            text = "Create your profile",
            fontSize = 18.textSdp,
            fontWeight = FontWeight.SemiBold,
            color = lightGray
        )

        Spacer(modifier = Modifier.height(4.sdp))

        Text(text = "Congratulations on taking the first step towards feeling more organized",
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Normal,
            color = lightGray
        )

        Spacer(modifier = Modifier.height(24.sdp))


        //Main card
        Card(
            modifier = Modifier
                .fillMaxWidth().wrapContentWidth()
                .padding(horizontal = 8.sdp)
                .background(Color.Transparent),
            shape = RoundedCornerShape(10.sdp),
            colors = CardDefaults.cardColors(containerColor =Color.Transparent),
            border = BorderStroke(width = 0.3.dp, color = lightGray),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(12.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Your profile",
                    fontSize = 15.textSdp,
                    fontWeight = FontWeight.SemiBold,
                    color = lightGray,
                    textAlign = TextAlign.Center
                )

                //profilePicture Option
                Card(modifier = Modifier.size(90.sdp).padding(vertical = 8.sdp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor =Color.Transparent),
                    border = BorderStroke(width = 0.3.dp, color = lightGray)) {
                    Image(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().padding(20.sdp),
                        colorFilter = ColorFilter.tint(lightGray)
                    )
                }

                //Upload Photo
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(start = 16.sdp, end = 16.sdp, top = 12.sdp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(width = 0.3.dp, color = Color.White),
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        ) {
                        Image(
                            imageVector =  Icons.Default.AccountCircle,
                            contentDescription = null, modifier = Modifier.size(25.sdp),
                            colorFilter = ColorFilter.tint(lightGray)
                        )

                        Text(text = "Upload Photo",
                            modifier = Modifier.padding(start = 8.sdp),
                            color = lightGray,
                            fontSize = 13.textSdp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }


                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    placeholder = { Text("Enter your Name...") },
                    modifier = Modifier.fillMaxWidth().padding(start = 12.sdp, end = 12.sdp, top = 12.sdp),
                    singleLine = true,
                    colors =  OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = red,   // Border color when focused
                        unfocusedBorderColor = lightGray, // Border color when not focused
                        cursorColor = red,          // Cursor color
                        focusedLabelColor = red,
                        unfocusedLabelColor = lightGray,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

            }

        }

    }
}
