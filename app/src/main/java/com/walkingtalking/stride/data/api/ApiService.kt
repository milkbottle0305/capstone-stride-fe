package com.walkingtalking.stride.data.api

import com.walkingtalking.stride.data.dto.LoginResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Field("type") type: String, @Field("id") id: String): LoginResponse
}