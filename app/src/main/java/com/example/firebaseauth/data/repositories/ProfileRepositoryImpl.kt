package com.example.firebaseauth.data.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.example.firebaseauth.domain.model.Profile
import com.example.firebaseauth.domain.repositories.ProfileRepository
import com.example.firebaseauth.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    storage: FirebaseStorage
) : ProfileRepository {

    override val profilesRef: CollectionReference = firestore.collection("profiles")

    private val imageRef =
        storage.reference.child("images/${firebaseAuth.currentUser?.uid}/profileImg.jpg")

    override suspend fun createProfile(
        username: String,
        email: String
    ): Result<Profile> {
        return try {
            val profile = Profile(
                name = username,
                email = email
            )
            firebaseAuth.currentUser?.uid?.let { id ->
                profilesRef.document(id)
                    .set(profile)
                    .await()
            }
            Result.Success(profile)
        } catch (e: Exception) {
            Log.d("Profile", e.message.toString())
            Result.Error(e)
        }

    }

    override suspend fun getProfile(): Result<Profile> {
        return try {
            var profile = Profile()
            firebaseAuth.currentUser?.uid?.let { id ->
                profilesRef.document(id)
                    .get()
                    .addOnSuccessListener { document ->
                        profile = document.toObject(Profile::class.java)!!
                    }.await()
            }
            Result.Success(profile)
        } catch (e: Exception) {
            Log.d("Get Profile", e.message.toString())
            Result.Error(e)
        }
    }

    override suspend fun uploadImage(
        uri: Uri
    ): Result<Uri> {
        return try {
            imageRef.putFile(uri).await()
            val downloadUrl = imageRef.downloadUrl.await()
            Log.d("RepoUri", downloadUrl.toString())
            Result.Success(downloadUrl)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun downloadImage(): Result<Bitmap> {
        return try {
            val filePath = File.createTempFile("profileImg", "bmp")
            imageRef.getFile(filePath).await()
            val bitmap = BitmapFactory.decodeFile(filePath.absolutePath)
            Result.Success(bitmap)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


    override suspend fun updateProfile(
        profile: Profile
    ): Result<Boolean> {
        return try {
            profilesRef.document(firebaseAuth.currentUser!!.uid)
                .set(profile)
                .await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}