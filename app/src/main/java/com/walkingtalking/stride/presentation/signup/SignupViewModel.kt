package com.walkingtalking.stride.presentation.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    private val _gender = mutableStateOf("")
    val gender: State<String> = _gender
    private val _ageRange = mutableStateOf("")
    val ageRange: State<String> = _ageRange
    private val _isNextButtonEnabled = mutableStateOf(false)
    val isNextButtonEnabled: State<Boolean> = _isNextButtonEnabled
    private val _nickname = mutableStateOf("")
    val nickname: State<String> = _nickname
    private val _isCompleteButtonEnabled = mutableStateOf(false)
    val isCompleteButtonEnabled: State<Boolean> = _isCompleteButtonEnabled

    init {
        Log.d("SignupViewModel", "SignupViewModel created $this")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("SignupViewModel", "SignupViewModel destroyed $this")
    }

    fun onGenderSelected(gender: String) {
        _gender.value = gender
        updateIsNextButtonEnabled()
    }

    fun onAgeRangeSelected(ageRange: String) {
        _ageRange.value = ageRange
        updateIsNextButtonEnabled()
    }

    private fun updateIsNextButtonEnabled() {
        _isNextButtonEnabled.value = _gender.value.isNotEmpty() && _ageRange.value.isNotEmpty()
    }


    fun setNickname(nickname: String) {
        _nickname.value = nickname
        updateIsCompleteButtonEnabled()
    }

    private fun updateIsCompleteButtonEnabled() {
        _isCompleteButtonEnabled.value = _nickname.value.length <= 8 && _nickname.value.isNotEmpty()
    }
}