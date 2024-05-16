package com.walktalk.stride.presentation.login

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.walktalk.stride.data.datasource.SharedPreferences
import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.repository.LoginRepository
import com.walktalk.stride.presentation.navigation.Screen
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val loginRepository = LoginRepository()

    companion object {
        private const val TAG = "LoginViewModel"
    }

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext


    fun kakaoLogin(navController: NavController) {
        val launch = viewModelScope.launch {
            handleKakaoLogin(navController)
            val kakaoUserId = SharedPreferences(context).getStringPref("kakaoUserId")
        }
    }

    private suspend fun handleKakaoLogin(navController: NavController) =
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
                    saveKakaoUserId(navController) { success ->
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
    private fun saveKakaoUserId(
        navController: NavController,
        callback: (success: Boolean) -> Unit
    ) {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null || tokenInfo == null) {
                // 개인 식별 ID 획득 실패
                callback(false)
            } else {
                // 개인 식별 ID 획득 성공
                val kakaoUserId = tokenInfo.id.toString()
                callback(true)
                navController.navigate(Screen.SignupGenderAge.route)
                SharedPreferences(context).setStringPref("kakaoUserId", kakaoUserId)
                viewModelScope.launch {
                    val request = LoginRequest("kakao", kakaoUserId)
                    val response = loginRepository.login(request)
                    Log.d(TAG, "response: $response")
                    SharedPreferences(context).setStringPref("accessToken", response.accessToken)
                    SharedPreferences(context).setStringPref("refreshToken", response.refreshToken)
                    Log.d(
                        TAG,
                        "accessToken: ${SharedPreferences(context).getStringPref("accessToken")}\n" +
                                "refreshToken: ${SharedPreferences(context).getStringPref("refreshToken")}\n" +
                                "kakaoUserId: $kakaoUserId"
                    )
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
