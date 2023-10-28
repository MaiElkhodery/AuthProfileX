package com.example.firebaseauth.presentation.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.data.repositories.AuthRepositoryImpl
import com.example.firebaseauth.data.repositories.ProfileRepositoryImpl
import com.example.firebaseauth.utils.HelperClass
import com.example.firebaseauth.utils.Result
import com.example.firebaseauth.utils.isEmailValid
import com.example.firebaseauth.utils.isPasswordValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepositoryImpl,
    private val profileRepo: ProfileRepositoryImpl
) : ViewModel() {

    @Inject
    lateinit var helper: HelperClass

    private var _email = MutableStateFlow("")
    var email = _email.asStateFlow()

    private var _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    private var _username = MutableStateFlow("")
    var username = _username.asStateFlow()

    private var _isAuthenticated: MutableStateFlow<Boolean> =
        MutableStateFlow(authRepo.currentUser != null)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    fun onEvent(event: AuthEvents) {
        when (event) {
            is AuthEvents.ChangeEmail -> {
                _email.value = event.email
            }

            is AuthEvents.ChangePassword -> {
                _password.value = event.password
            }

            is AuthEvents.ChangeUserName -> {
                _username.value = event.username
            }
        }
    }

    fun signup() {
        val username = _username.value
        val email = _email.value
        val password = _password.value
        if (!isEmailValid(email)) {
            helper.showToast("Email Not Valid")
            return
        }
        if (!isPasswordValid(password)) {
            helper.showToast("Password Not Valid")
            return
        }
        if (username.isBlank()) {
            helper.showToast("Enter Username")
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = authRepo.signup(
                name = username,
                email = email,
                password = password
            )) {
                is Result.Error -> {
                    helper.handleFirebaseAuth(result.exception)
                }
                is Result.Success -> {
                    _isAuthenticated.value = true
                    profileRepo.createProfile(username, email)
                }
            }
            _isLoading.value = false
        }
    }

    fun login() {
        if (!isEmailValid(_email.value)) {
            helper.showToast("Email Not Valid")
            return
        }
        if (!isPasswordValid(_password.value)) {
            helper.showToast("Password Not Valid")
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = authRepo.login(
                email = _email.value,
                password = _password.value
            )) {
                is Result.Error -> {
                    helper.handleFirebaseAuth(result.exception)
                }

                is Result.Success -> {
                    _isAuthenticated.value = true
                }
            }
            _isLoading.value = false
        }
    }
}