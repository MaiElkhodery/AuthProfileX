package com.example.firebaseauth.presentation.authentication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauth.R

@Composable
fun ShowWarningText(text: String) {
    Text(
        modifier = Modifier
            .padding(3.dp)
            .wrapContentSize(),
        text = text,
        color = Color.Gray,
        fontSize = 12.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_medium
            )
        )
    )
}