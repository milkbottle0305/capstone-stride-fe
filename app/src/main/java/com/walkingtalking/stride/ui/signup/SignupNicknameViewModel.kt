package com.walkingtalking.stride.ui.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignupNicknameViewModel : ViewModel() {

    private val _nickname = mutableStateOf("")
    val nickname: State<String> = _nickname
    private val _isEnabled = mutableStateOf(false)
    val isEnabled: State<Boolean> = _isEnabled

    fun setNickname(nickname: String) {
        _nickname.value = nickname
        updateIsNextButtonEnabled()
    }

    private fun updateIsNextButtonEnabled() {
        _isEnabled.value = _nickname.value.length <= 8 && _nickname.value.isNotEmpty()
    }
}