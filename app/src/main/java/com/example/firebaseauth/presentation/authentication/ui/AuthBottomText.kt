package com.example.firebaseauth.presentation.authentication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauth.R

@Composable
fun ShowAuthBottomText(
    text1: String,
    text2: String,
    onClickText : ()->Unit
    ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text1,
            fontSize = 11.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_medium
                )
            ),
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .padding(start = 6.dp)
                .clickable {
                           onClickText()
                },
            text = text2,
            fontSize = 12.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_medium
                )
            ),
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }

}