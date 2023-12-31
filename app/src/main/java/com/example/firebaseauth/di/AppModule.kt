package com.example.firebaseauth.di


import android.content.Context
import com.example.firebaseauth.data.repositories.AuthRepositoryImpl
import com.example.firebaseauth.data.repositories.ProfileRepositoryImpl
import com.example.firebaseauth.domain.repositories.AuthRepository
import com.example.firebaseauth.domain.repositories.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideStorageRef(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideContext(
        @ApplicationContext appContext: Context
    ): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideProfileRepositoryImpl(impl: ProfileRepositoryImpl): ProfileRepository = impl
}