package com.walktalk.stride.data.api

import android.content.Context
import android.util.Log
import com.walktalk.stride.data.datasource.SharedPreferences
import com.walktalk.stride.data.dto.request.RefreshTokenRequest
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

@Suppress("NAME_SHADOWING")
class TokenInterceptor(private val context: Context) : Interceptor {
    companion object {
        private const val TAG = "TokenInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        // 1. MobileToken을 꺼내 Request Header에 넣고 요청을 보낸다.
        val accessToken = SharedPreferences(context).getStringPref("accessToken")
            ?: return chain.proceed(chain.request())
        val tokenAddedRequest = chain.request().newBuilder()
            .addHeader("authorization", "Bearer $accessToken")
            .build()

        val response = chain.proceed(tokenAddedRequest)

        // 2. 위 Response에서 응답 json을 꺼내 서버 응답 코드가 토큰 만료 에러 코드인지 확인한다.
        if (response.code() == 401) { // 응답 토큰이 만료되었는지에 대한 메서드는 비공개합니다!

            // 4. MobileTokenRepository 로부터 갱신된 토큰(Refreshed Token)을 가져온다.
            var refreshedAccessToken: String
            var refreshedRefreshToken: String
            try {
                runBlocking {
                    val request = RefreshTokenRequest(accessToken)
                    val response =
                        RetrofitInstance.apiService.refreshToken(request)
                    refreshedAccessToken = response.body()!!.accessToken
                    refreshedRefreshToken = response.body()!!.refreshToken
                    SharedPreferences(context).setStringPref(
                        "accessToken",
                        refreshedAccessToken
                    )
                    SharedPreferences(context).setStringPref(
                        "refreshToken",
                        refreshedRefreshToken
                    )
                }
                // 5. chain의 Request 객체를 복사해 재발급한 토큰을 Header에 넣고 요청을 보낸다.
                val refreshedRequest = chain.request().newBuilder()
                    .addHeader("authorization", "Bearer $refreshedAccessToken")
                    .build()
                return chain.proceed(refreshedRequest)
            } catch (e: Exception) {
                Log.e(TAG, "토큰 갱신 실패", e)
            }
        }
        // 3. 토큰 만료에러가 아니면 응답을 그대로 반환 한다.
        return response
    }
}