package com.example.firebaseauth.domain.model

data class Profile(
    var name: String = "",
    var email: String = "",
    var linkedin: String = "",
    var facebook: String = "",
    var phoneNumber: String = "",
    var about: String = "",
    var uri: String = ""
)
