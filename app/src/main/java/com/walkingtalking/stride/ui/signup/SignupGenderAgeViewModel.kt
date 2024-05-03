package com.walkingtalking.stride.ui.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignupGenderAgeViewModel : ViewModel() {
    private val _gender = mutableStateOf("")
    val gender: State<String> = _gender
    private val _ageRange = mutableStateOf("")
    val ageRange: State<String> = _ageRange
    private val _isEnabled = mutableStateOf(false)
    val isEnabled: State<Boolean> = _isEnabled

    fun onGenderSelected(gender: String) {
        _gender.value = gender
        updateIsNextButtonEnabled()
    }

    fun onAgeRangeSelected(ageRange: String) {
        _ageRange.value = ageRange
        updateIsNextButtonEnabled()
    }

    private fun updateIsNextButtonEnabled() {
        _isEnabled.value = _gender.value.isNotEmpty() && _ageRange.value.isNotEmpty()
    }

    init {
        Log.d("ViewModel", "SignupGenderAgeViewModel initialized!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "SignupGenderAgeViewModel cleared!")
    }
}