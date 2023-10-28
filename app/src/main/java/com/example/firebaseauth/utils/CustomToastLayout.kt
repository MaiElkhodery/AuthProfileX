package com.example.firebaseauth.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebaseauth.R

@Composable
fun CustomToast(
    text:String
){
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        Text(
            modifier = Modifier,
            text = text ,
            )
    }
}

@Preview
@Composable
fun CustomToastPreview() {
    CustomToast("This is custom Toast")
}