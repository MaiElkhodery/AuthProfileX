package com.example.firebaseauth.domain.repositories

import android.graphics.Bitmap
import android.net.Uri
import com.example.firebaseauth.domain.model.Profile
import com.example.firebaseauth.utils.Result
import com.google.firebase.firestore.CollectionReference

interface ProfileRepository {

    val profilesRef: CollectionReference?
    suspend fun createProfile(
        username: String,
        email: String
    ): Result<Profile>

    suspend fun updateProfile(
        profile: Profile
    ): Result<Boolean>

    suspend fun getProfile(): Result<Profile>

    suspend fun uploadImage(uri: Uri): Result<Uri>

    suspend fun downloadImage(): Result<Bitmap>
}