package com.walkingtalking.stride.data.datasource

import com.walkingtalking.stride.data.dto.LoginResponse
import com.walkingtalking.stride.network.ApiService

class RemoteDataSource(private val apiService: ApiService) : IDataSource {
    override suspend fun login(type: String, id: String): LoginResponse {
        return apiService.login(type, id)
    }
}