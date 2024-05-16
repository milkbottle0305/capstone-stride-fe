package com.walktalk.stride.presentation.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walktalk.stride.data.dto.request.UserDataRequest
import com.walktalk.stride.data.repository.SignupRepository
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    private val TAG = "SignupViewModel"

    private val signupRepository = SignupRepository()
    private val _genderIndex = mutableStateOf<Int?>(null)
    val genderIndex: State<Int?> = _genderIndex
    private val _ageIndex = mutableStateOf<Int?>(null)
    val ageIndex: State<Int?> = _ageIndex
    private val _isNextButtonEnabled = mutableStateOf(false)
    val isNextButtonEnabled: State<Boolean> = _isNextButtonEnabled
    private val _nickname = mutableStateOf("")
    val nickname: State<String> = _nickname
    private val _isCompleteButtonEnabled = mutableStateOf(false)
    val isCompleteButtonEnabled: State<Boolean> = _isCompleteButtonEnabled

    private var _setUserDataResult = mutableStateOf<Boolean?>(null)
    val setUserDataResult: State<Boolean?> = _setUserDataResult

    init {
        Log.d("SignupViewModel", "SignupViewModel created $this")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("SignupViewModel", "SignupViewModel destroyed $this")
    }

    fun onGenderSelected(index: Int) {
        _genderIndex.value = index
        updateIsNextButtonEnabled()
    }

    fun onAgeRangeSelected(index: Int) {
        _ageIndex.value = index
        updateIsNextButtonEnabled()
    }

    private fun updateIsNextButtonEnabled() {
        _isNextButtonEnabled.value = _genderIndex.value != null && _ageIndex.value != null
    }


    fun setNickname(nickname: String) {
        _nickname.value = nickname
        updateIsCompleteButtonEnabled()
    }

    private fun updateIsCompleteButtonEnabled() {
        _isCompleteButtonEnabled.value = _nickname.value.length <= 8 && _nickname.value.isNotEmpty()
    }

    fun setUserData() {
        viewModelScope.launch {
            try {
                if (genderIndex.value == null || ageIndex.value == null) {
                    return@launch
                }
                var genderAttribute: String = ""
                when (genderIndex.value) {
                    0 -> genderAttribute = "man"
                    1 -> genderAttribute = "woman"
                }
                var ageAttribute: String = ""
                when (ageIndex.value) {
                    0 -> ageAttribute = "~50"
                    1 -> ageAttribute = "50~55"
                    2 -> ageAttribute = "55~60"
                    3 -> ageAttribute = "60~70"
                    4 -> ageAttribute = "70~80"
                    5 -> ageAttribute = "80~"
                }
                val request = UserDataRequest(
                    gender = genderAttribute,
                    age = ageAttribute,
                    nickname = nickname.value
                )
                val response = signupRepository.setUserData(request)
                Log.d(TAG, "setUserData: $response")
                _setUserDataResult.value = request == response
            } catch (e: Exception) {
                val e1 = Log.e("SignupViewModel", "Failed to set user data", e)
                _setUserDataResult.value = false
            }
        }
    }
}