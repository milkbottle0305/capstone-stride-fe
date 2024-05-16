package com.walktalk.stride.data.api

import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.dto.request.NicknameRequest
import com.walktalk.stride.data.dto.request.RefreshTokenRequest
import com.walktalk.stride.data.dto.request.UserDataRequest
import com.walktalk.stride.data.dto.request.UserProfileRequest
import com.walktalk.stride.data.dto.response.LoginResponse
import com.walktalk.stride.data.dto.response.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {

    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("/api/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): RefreshTokenResponse

    @POST("/api/user")
    suspend fun setUserData(@Body request: UserDataRequest): UserDataRequest

    @PATCH("/api/user/profile")
    suspend fun updateUserProfile(@Body request: UserProfileRequest): Boolean

    @PATCH("/api/user/nickname")
    suspend fun updateUserNickname(@Body request: NicknameRequest): Boolean


}