package com.example.firebaseauth.presentation.profile.viewmodel

import android.net.Uri

sealed class ProfileEvent {
    data class ChangeName(
        val name: String
    ) : ProfileEvent()

    data class ChangeEmail(
        val email: String
    ) : ProfileEvent()

    data class ChangeLinkedinUrl(
        val url: String
    ) : ProfileEvent()

    data class ChangeFacebookUrl(
        val url: String
    ) : ProfileEvent()

    data class ChangePhoneNumber(
        val phoneNumber: String
    ) : ProfileEvent()

    data class ChangeAboutMe(
        val aboutMe: String
    ) : ProfileEvent()

    data class ChangePhoto(
        val uri: Uri
    ):ProfileEvent()
    object Save : ProfileEvent()
}