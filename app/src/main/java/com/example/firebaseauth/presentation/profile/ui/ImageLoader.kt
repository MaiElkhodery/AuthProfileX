package com.example.firebaseauth.presentation.profile.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.firebaseauth.R
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(
    uri: StateFlow<Uri?>,
    onChange: (Uri) -> Unit
) {
    val selectedImgUri = uri.collectAsState().value
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                onChange(uri)
            }
        }
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        if (selectedImgUri != null) {
            GlideImage(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                model = selectedImgUri,
                contentDescription = null
            )
        } else {
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null
            )
        }
        Icon(
            modifier = Modifier
                .size(30.dp)
                .padding(bottom = 3.dp, end = 3.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    launcher.launch("image/*")
                },
            painter = painterResource(
                id = R.drawable.attachment
            ),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ImagePreview() {
    //LoadImage()
}