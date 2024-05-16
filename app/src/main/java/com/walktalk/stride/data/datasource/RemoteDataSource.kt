package com.walktalk.stride.data.datasource

import com.walktalk.stride.data.api.ApiService
import com.walktalk.stride.data.api.RetrofitInstance
import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.dto.request.RefreshTokenRequest
import com.walktalk.stride.data.dto.request.UserDataRequest
import com.walktalk.stride.data.dto.request.UserProfileRequest
import com.walktalk.stride.data.dto.response.LoginResponse
import com.walktalk.stride.data.dto.response.RefreshTokenResponse

class RemoteDataSource {
    private val apiService: ApiService = RetrofitInstance.apiService

    suspend fun login(request: LoginRequest): LoginResponse = apiService.login(request)
    suspend fun setUserData(request: UserDataRequest): UserDataRequest =
        apiService.setUserData(request)

    suspend fun updateUserProfile(request: UserProfileRequest): Boolean =
        apiService.updateUserProfile(request)

    suspend fun refreshToken(request: RefreshTokenRequest): RefreshTokenResponse =
        apiService.refreshToken(request)

}