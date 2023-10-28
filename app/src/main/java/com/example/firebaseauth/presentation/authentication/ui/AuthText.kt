package com.example.firebaseauth.presentation.authentication.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.firebaseauth.R

@Composable
fun ShowAuthText(authText: String) {
    Text(
        text = authText,
        fontSize = 26.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_medium
            )
        ),
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
@Preview
fun PreviewT() {
    ShowAuthText("sing up")
}