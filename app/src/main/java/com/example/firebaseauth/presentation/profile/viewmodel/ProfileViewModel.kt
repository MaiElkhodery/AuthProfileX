package com.example.firebaseauth.presentation.profile.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.data.repositories.ProfileRepositoryImpl
import com.example.firebaseauth.domain.model.Profile
import com.example.firebaseauth.utils.HelperClass
import com.example.firebaseauth.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepo: ProfileRepositoryImpl
) : ViewModel() {

    @Inject
    lateinit var helper: HelperClass

    private var _profileImgUri = MutableStateFlow<Uri?>(null)
    val profileImgUri = _profileImgUri.asStateFlow()


    private var _fullName = MutableStateFlow("")
    val fullName = _fullName.asStateFlow()

    private var _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private var _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private var _facebook = MutableStateFlow("")
    val facebook = _facebook.asStateFlow()

    private var _linkedin = MutableStateFlow("")
    val linkedin = _linkedin.asStateFlow()

    private var _about = MutableStateFlow("")
    var about = _about.asStateFlow()

    private var _change = MutableStateFlow(0)
    val change = _change.asStateFlow()

    private val _saveClicked = MutableStateFlow(false)
    val saveClicked = _saveClicked.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getProfile()
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ChangeAboutMe -> {
                _about.value = event.aboutMe
                _change.value = 1
            }

            is ProfileEvent.ChangeEmail -> {
                _email.value = event.email
                _change.value = 1
            }

            is ProfileEvent.ChangeFacebookUrl -> {
                _facebook.value = event.url
                _change.value = 1
            }

            is ProfileEvent.ChangeLinkedinUrl -> {
                _linkedin.value = event.url
                _change.value = 1
            }

            is ProfileEvent.ChangeName -> {
                _fullName.value = event.name
                _change.value = 1
            }

            is ProfileEvent.ChangePhoneNumber -> {
                _phoneNumber.value = event.phoneNumber
                _change.value = 1
            }

            is ProfileEvent.ChangePhoto -> {
                _profileImgUri.value = event.uri
                _change.value = 1
                //uploadImg(event.uri)
            }

            is ProfileEvent.Save -> {
                _saveClicked.value = true
                if (
                    _fullName.value.isBlank() ||
                    _email.value.isBlank() ||
                    _phoneNumber.value.isBlank() ||
                    _about.value.isBlank() ||
                    _facebook.value.isBlank() ||
                    _linkedin.value.isBlank()
                ) {
                    return
                }
                uploadImgAndUpdateProfile()
            }
        }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            val profile = Profile(
                email = _email.value,
                name = _fullName.value,
                phoneNumber = _phoneNumber.value,
                linkedin = _linkedin.value,
                facebook = _facebook.value,
                about = _about.value,
                uri = _profileImgUri.value.toString()
            )
            when (val result = profileRepo.updateProfile(profile)) {
                is Result.Error -> {
                    Log.d("Update Profile", result.exception.message.toString())
                    helper.showToast("Try Again")
                    _change.value = 1
                }

                is Result.Success -> {
                    _change.value = 0
                }
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = profileRepo.getProfile()) {
                is Result.Error -> {
                    Log.d("Get Profile", result.exception.message.toString())
                }

                is Result.Success -> {
                    _fullName.value = result.result.name
                    _email.value = result.result.email
                    _about.value = result.result.about
                    _linkedin.value = result.result.linkedin
                    _facebook.value = result.result.facebook
                    _phoneNumber.value = result.result.phoneNumber
                    _profileImgUri.value = Uri.parse(result.result.uri)
                }
            }
            Log.d("ImageUri", _profileImgUri.value.toString())
            _isLoading.value = false
        }
    }

    private fun uploadImgAndUpdateProfile() {
        viewModelScope.launch {
            _isLoading.value=true
            when (val result = profileRepo.uploadImage(_profileImgUri.value!!)) {
                is Result.Error -> {
                    Log.d("Upload Image", result.exception.message.toString())
                    _change.value = 1
                }

                is Result.Success -> {
                    _profileImgUri.value = result.result
                    _change.value = 0
                    Log.d("ImageUri", _profileImgUri.value.toString())
                    updateProfile()
                }
            }
            _isLoading.value=false
        }
    }

}