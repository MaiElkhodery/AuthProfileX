package com.example.firebaseauth.presentation.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebaseauth.R
import com.example.firebaseauth.presentation.authentication.ui.ShowProgressIndicator
import com.example.firebaseauth.presentation.authentication.ui.VerticalSpacer
import com.example.firebaseauth.presentation.profile.viewmodel.ProfileEvent
import com.example.firebaseauth.presentation.profile.viewmodel.ProfileViewModel
import com.example.firebaseauth.ui.theme.ProfileInputTextColor

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val loading = viewModel.isLoading.collectAsState().value
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(scrollState)
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (loading) {
            ShowProgressIndicator()
        }
        VerticalSpacer()
        Text(
            modifier = Modifier
                .weight(0.3f)
                .clickable {
                    viewModel.onEvent(ProfileEvent.Save)
                },
            text = stringResource(id = R.string.save),
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_medium
                )
            ),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = if (viewModel.change.collectAsState().value == 0) {
                Color.Gray
            } else {
                ProfileInputTextColor
            }
        )
        Column(
            modifier = Modifier
                .weight(1.5f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoadImage(viewModel.profileImgUri) { uri ->
                viewModel.onEvent(ProfileEvent.ChangePhoto(uri))
            }
        }
        Column(
            modifier = Modifier
                .weight(6f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            VerticalSpacer()
            Text(
                text = stringResource(id = R.string.personal_info),
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_medium
                    )
                ),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ProfileInputTextColor
            )
            VerticalSpacer()
            ShowProfileTextField(
                label = stringResource(id = R.string.fullName),
                text = viewModel.fullName,
                saveClicked = viewModel.saveClicked.collectAsState().value,
                onChangeValue = { name ->
                    viewModel.onEvent(ProfileEvent.ChangeName(name))
                }
            )
            VerticalSpacer()
            ShowProfileTextField(
                label = stringResource(id = R.string.aboutMe),
                text = viewModel.about,
                saveClicked = viewModel.saveClicked.collectAsState().value,
                onChangeValue = { about ->
                    viewModel.onEvent(ProfileEvent.ChangeAboutMe(about))
                }
            )
            VerticalSpacer()
            ShowProfileTextField(
                label = stringResource(id = R.string.phoneNumber),
                text = viewModel.phoneNumber,
                saveClicked = viewModel.saveClicked.collectAsState().value,
                onChangeValue = { number ->
                    viewModel.onEvent(ProfileEvent.ChangePhoneNumber(number))
                }
            )
            VerticalSpacer()
            ShowProfileTextField(
                label = stringResource(id = R.string.email),
                text = viewModel.email,
                saveClicked = viewModel.saveClicked.collectAsState().value,
                onChangeValue = { email ->
                    viewModel.onEvent(ProfileEvent.ChangeEmail(email))
                }
            )
            VerticalSpacer()
            ShowProfileTextField(
                label = stringResource(id = R.string.linkedin),
                text = viewModel.linkedin,
                saveClicked = viewModel.saveClicked.collectAsState().value,
                onChangeValue = { url ->
                    viewModel.onEvent(ProfileEvent.ChangeLinkedinUrl(url))
                }
            )
            VerticalSpacer()
            ShowProfileTextField(
                label = stringResource(id = R.string.facebook),
                text = viewModel.facebook,
                saveClicked = viewModel.saveClicked.collectAsState().value,
                onChangeValue = { url ->
                    viewModel.onEvent(ProfileEvent.ChangeFacebookUrl(url))
                }
            )
            VerticalSpacer()
        }
    }
}

@Composable
@Preview
fun PreviewProfile() {
    ProfileScreen()
}