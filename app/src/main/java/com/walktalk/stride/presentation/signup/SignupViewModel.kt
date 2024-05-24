package com.walktalk.stride.presentation.signup

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walktalk.stride.data.dto.request.UserDataRequest
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.repository.SignupRepository
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    companion object {
        private const val TAG = "SignupViewModel"
    }

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
    var isCompleteButtonEnabled: State<Boolean> = _isCompleteButtonEnabled
        private set

    private val _setUserDataApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val setUserDataApiState: State<ApiState<String>> = _setUserDataApiState

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
            _setUserDataApiState.value = ApiState.Loading
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
                if (response == request) {
                    _setUserDataApiState.value = ApiState.Success("Success")
                } else {
                    _setUserDataApiState.value = ApiState.Error("Failed to set user data")
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "An unknown error occurred")
                _setUserDataApiState.value =
                    ApiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun resetApiState() {
        Log.d(TAG, "resetApiState")
        _setUserDataApiState.value = ApiState.Empty
    }
}