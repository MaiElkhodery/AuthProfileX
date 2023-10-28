package com.example.firebaseauth.domain.repositories

import com.example.firebaseauth.utils.Result
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser:FirebaseUser?

    suspend fun signup(
        name:String,
        email:String,
        password:String
    ): Result<FirebaseUser>
    suspend fun login(
        email:String,
        password:String
    ): Result<FirebaseUser>

}