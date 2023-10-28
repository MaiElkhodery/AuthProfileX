package com.example.firebaseauth.presentation.authentication.viewmodel

sealed class AuthEvents {
    data class ChangeUserName(
        val username: String
    ) : AuthEvents()

    data class ChangeEmail(
        val email: String
    ) : AuthEvents()

    data class ChangePassword(
        val password: String
    ) : AuthEvents()
}
