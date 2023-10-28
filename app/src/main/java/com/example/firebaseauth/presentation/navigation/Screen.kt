package com.example.firebaseauth.presentation.navigation

sealed class Screen(
    val route: String
) {
    object LoginScreen : Screen("login")
    object SignupScreen : Screen("signup")

    object ProfileScreen:Screen("profile")
}