package com.example.firebaseauth.utils

sealed class Result<out R> {
    data class Success<out R>(val result: R) : Result<R>()
    data class Error(val exception: Exception) : Result<Nothing>()
}