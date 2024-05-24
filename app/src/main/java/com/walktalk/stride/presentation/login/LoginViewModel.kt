package com.walktalk.stride.presentation.login

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.walktalk.stride.data.datasource.SharedPreferences
import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val loginRepository = LoginRepository()

    companion object {
        private const val TAG = "LoginViewModel"
    }

    private val context = application
    private val _loginApiState = mutableStateOf<ApiState<String>>(ApiState.Empty)
    val loginApiState: State<ApiState<String>> = _loginApiState


    fun createKakaoToken() {
        _loginApiState.value = ApiState.Loading
        // 로그인 조합 예제
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                _loginApiState.value = ApiState.Error("카카오계정으로 로그인 실패")
            } else if (token != null) {
                saveKakaoUserId()
            }
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context = context) { token, error ->
                if (error != null) {
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        _loginApiState.value = ApiState.Error("카카오톡 로그인 취소")
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    saveKakaoUserId()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context = context, callback = callback)
        }
    }

    // 개인 식별 ID를 저장
    private fun saveKakaoUserId() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null || tokenInfo == null) {
                // 개인 식별 ID 획득 실패
                _loginApiState.value = ApiState.Error("카카오톡 로그인 실패")
            } else {
                // 개인 식별 ID 획득 성공
                val kakaoUserId = tokenInfo.id.toString()
                SharedPreferences(context).setStringPref("kakaoUserId", kakaoUserId)
                viewModelScope.launch {
                    val request = LoginRequest("kakao", kakaoUserId)
                    try {
                        val response = loginRepository.login(request)
                        SharedPreferences(context).setStringPref(
                            "accessToken",
                            response.accessToken
                        )
                        SharedPreferences(context).setStringPref(
                            "refreshToken",
                            response.refreshToken
                        )
                        if (response.needInitialization) {
                            _loginApiState.value = ApiState.Success("카카오톡 로그인 성공: 초기화 필요")
                        } else {
                            _loginApiState.value = ApiState.Success("카카오톡 로그인 성공: 초기화 불필요")
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                        _loginApiState.value = ApiState.Error("카카오톡 로그인 실패")
                    }
                }
            }
        }
    }

    fun googleLogin() {
        viewModelScope.launch {
            // TODO: 구글 로그인
        }
    }

}
