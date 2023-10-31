package com.example.firebaseauth.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.net.toUri
import com.example.firebaseauth.domain.model.Profile
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
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
    }

    fun generatePDF(
        profile: Profile, bitmap: Bitmap
    ) {
        val document = PdfDocument()
        val pageMetaData = PdfDocument.PageInfo.Builder(792, 1000, 1).create()
        val page = document.startPage(pageMetaData)
        val canvas = page.canvas
        val textStyle = Paint()
        val paint = Paint()

        val scaleBitmap = Bitmap.createScaledBitmap(bitmap, 170, 170, false)
        canvas.drawBitmap(scaleBitmap, 40f, 100f, paint)

        textStyle.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        textStyle.color = Color.Black.toArgb()
        textStyle.textSize = 30f

        val x = 250f
        val y = 120f
        canvas.drawText("Name: ${profile.name}", x, y, textStyle)
        canvas.drawText("About Me: ${profile.about}", x, y + 100f, textStyle)
        canvas.drawText("Email: ${profile.email}", x, y + 200f, textStyle)
        canvas.drawText("Phone Number: ${profile.phoneNumber}", x, y + 300f, textStyle)
        canvas.drawText("Linkedin: ${profile.linkedin}", x, y + 400f, textStyle)
        canvas.drawText("Facebook: ${profile.facebook}", x, y + 500f, textStyle)

        document.finishPage(page)

        val fileDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString()
        )
        if (!fileDir.exists()) {
            fileDir.mkdirs() // Create the directory if it doesn't exist
        }
        val filePath = File(fileDir, "profile.pdf")
        if (filePath.exists()) {
            filePath.delete()
        }
        try {
            document.writeTo(FileOutputStream(filePath))
            showToast("PDF file generated..Check Documents")

        } catch (e: Exception) {
            Log.d("File Generation", e.message.toString())
            showToast("Fail to generate PDF file..")
        }
        Log.d("File", fileDir.toUri().toString())
        document.close()
    }
}
