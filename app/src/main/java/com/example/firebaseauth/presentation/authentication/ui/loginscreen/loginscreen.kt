package com.example.firebaseauth.presentation.authentication.ui.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.R
import com.example.firebaseauth.presentation.authentication.ui.ShowAuthBottomText
import com.example.firebaseauth.presentation.authentication.ui.ShowAuthButton
import com.example.firebaseauth.presentation.authentication.ui.ShowAuthText
import com.example.firebaseauth.presentation.authentication.ui.ShowPasswordTextField
import com.example.firebaseauth.presentation.authentication.ui.ShowProgressIndicator
import com.example.firebaseauth.presentation.authentication.ui.ShowTextField
import com.example.firebaseauth.presentation.authentication.ui.VerticalSpacer
import com.example.firebaseauth.presentation.authentication.viewmodel.AuthEvents
import com.example.firebaseauth.presentation.authentication.viewmodel.AuthViewModel
import com.example.firebaseauth.presentation.navigation.Screen


@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    val loading = viewModel.isLoading.collectAsState().value
    val isAuthenticated = viewModel.isAuthenticated.collectAsState().value
    if (isAuthenticated) {
        navController.navigate(Screen.ProfileScreen.route) {
            popUpTo(Screen.LoginScreen.route) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier.background(color = Color.White)

    ) {
        if (loading) {
            ShowProgressIndicator()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(2f),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 26.dp),
                horizontalAlignment = Alignment.Start
            ) {
                VerticalSpacer(Modifier.weight(0.5f))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    ShowAuthText(authText = stringResource(id = R.string.login))
                }
                VerticalSpacer(Modifier.weight(0.5f))
                Column(
                    modifier = Modifier
                        .weight(2.5f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    ShowTextField(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = R.string.email),
                        text = viewModel.email
                    ) { email ->
                        viewModel.onEvent(AuthEvents.ChangeEmail(email))
                    }
                    VerticalSpacer(Modifier.weight(0.5f))
                    ShowPasswordTextField(
                        modifier = Modifier.weight(1f),
                        text = viewModel.password
                    ) { password ->
                        viewModel.onEvent(AuthEvents.ChangePassword(password))
                    }
                    VerticalSpacer(Modifier.weight(0.5f))
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShowAuthButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.login)
                    ) {
                        viewModel.login()
                    }
                    VerticalSpacer(Modifier.weight(0.3f))
                    ShowAuthBottomText(
                        modifier = Modifier.weight(1f),
                        text1 = stringResource(id = R.string.create_account),
                        text2 = stringResource(id = R.string.signup)
                    ) {
                        navController.navigate(Screen.SignupScreen.route) {
                            popUpTo(Screen.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                    VerticalSpacer(Modifier.weight(0.5f))
                }
            }
        }


    }

}

@Composable
@Preview
fun PreviewLogin() {
    LoginScreen(navController = rememberNavController())
}
