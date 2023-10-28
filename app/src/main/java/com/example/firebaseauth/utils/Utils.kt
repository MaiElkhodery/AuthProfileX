package com.example.firebaseauth.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

fun isEmailValid(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}

fun isPasswordValid(password: String): Boolean {
    val minLength = 8
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }

    return password.length >= minLength && hasUpperCase && hasLowerCase && hasDigit
}

class HelperClass @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG
        toast
    }

    fun handleFirebaseAuth(e: Throwable) {
        when (e) {
            is FirebaseAuthInvalidUserException -> {

                showToast("this user does not exist")
            }

            is FirebaseAuthInvalidCredentialsException -> {
                showToast("wrong password")
            }

            is FirebaseAuthEmailException -> {
                showToast("wrong email")
            }

            is FirebaseAuthMissingActivityForRecaptchaException -> {
                showToast("this device blocked")
            }

            is FirebaseAuthUserCollisionException -> {
                showToast("email already in use")
            }

            is FirebaseNetworkException -> {
                showToast("network error")
            }

            else -> {
                showToast(e.message.toString())
            }
        }
        fun handleFirestore(e: Throwable) {
            when (e) {

                is FirebaseAuthInvalidCredentialsException -> {
                    showToast("wrong password")
                }

                is FirebaseAuthEmailException -> {
                    showToast("wrong email")
                }

                is FirebaseAuthMissingActivityForRecaptchaException -> {
                    showToast("this device blocked")
                }

                is FirebaseAuthUserCollisionException -> {
                    showToast("email already in use")
                }

                is FirebaseNetworkException -> {
                    showToast("network error")
                }

                else -> {
                    showToast(e.message.toString())
                }
            }

        }
    }
}
