package com.walkingtalking.stride.ui.login

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginViewModel(
    application: Application


) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    private val isLoggedIn = MutableStateFlow(false)
    private var kakaoUserId: String? = null

    fun kakaoLogin() {
        val launch = viewModelScope.launch {
            isLoggedIn.emit(handleKakaoLogin())
        }
    }

    private suspend fun handleKakaoLogin(): Boolean =
        suspendCoroutine<Boolean> { continuation ->
            // 로그인 조합 예제
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오계정으로 로그인 실패", error)
                    continuation.resume(false)
                } else if (token != null) {
                    Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                    saveKakaoUserId(token.accessToken) { success ->
                        if (success) {
                            continuation.resume(true)
                        } else {
                            continuation.resume(false)
                        }
                    }
                }
            }
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }

    // 개인 식별 ID를 저장
    private fun saveKakaoUserId(accessToken: String, callback: (success: Boolean) -> Unit) {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null || tokenInfo == null) {
                // 개인 식별 ID 획득 실패
                callback(false)
            } else {
                // 개인 식별 ID 획득 성공
                kakaoUserId = tokenInfo.id.toString()
                Log.d(TAG, "카카오 ID: $kakaoUserId")
                callback(true)
            }
        }
    }
}
