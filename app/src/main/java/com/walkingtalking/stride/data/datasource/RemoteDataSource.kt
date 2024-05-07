package com.walkingtalking.stride.data.datasource

import com.walkingtalking.stride.data.api.ApiService
import com.walkingtalking.stride.data.dto.LoginResponse

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun login(type: String, id: String): LoginResponse {
        return apiService.login(type, id)
    }
}