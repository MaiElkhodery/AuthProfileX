package com.example.firebaseauth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebaseauth.presentation.authentication.ui.loginscreen.LoginScreen
import com.example.firebaseauth.presentation.authentication.ui.signupscreen.SignupScreen
import com.example.firebaseauth.presentation.profile.ui.ProfileScreen

@Composable
fun NavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(
            route = Screen.SignupScreen.route,
        ) {
            SignupScreen(navController = navController)
        }
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen()
        }
    }
}