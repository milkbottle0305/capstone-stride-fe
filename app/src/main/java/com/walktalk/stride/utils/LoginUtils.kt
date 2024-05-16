package com.walktalk.stride.utils

import android.content.Context
import androidx.navigation.NavController
import com.walktalk.stride.data.datasource.SharedPreferences
import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.repository.LoginRepository
import com.walktalk.stride.presentation.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

object LoginUtils {
    private const val TAG = "LoginUtils"
    private val loginRepository = LoginRepository()

    suspend fun autoLogin(context: Context, navController: NavController): String {
        return CoroutineScope(Dispatchers.Main).async {
            var returnRoute = Screen.Login.route
            val kakaoUserId = SharedPreferences(context).getStringPref("kakaoUserId")
            val googleUserId = SharedPreferences(context).getStringPref("googleUserId")
            // 비동기 작업
            if (kakaoUserId != null) {
                val request = LoginRequest("kakao", kakaoUserId)
                val response = loginRepository.login(request)
                SharedPreferences(context).setStringPref("accessToken", response.accessToken)
                SharedPreferences(context).setStringPref("refreshToken", response.refreshToken)
                returnRoute = if (response.needInitialization) {
                    Screen.SignupGenderAge.route
                } else {
                    Screen.Main.route
                }
            } else if (googleUserId != null) {
                val request = LoginRequest("google", googleUserId)
                val response = loginRepository.login(request)
                SharedPreferences(context).setStringPref("accessToken", response.accessToken)
                SharedPreferences(context).setStringPref("refreshToken", response.refreshToken)
                returnRoute = if (response.needInitialization) {
                    Screen.SignupGenderAge.route
                } else {
                    Screen.Main.route
                }
            }
            return@async returnRoute
        }.await()
    }
}