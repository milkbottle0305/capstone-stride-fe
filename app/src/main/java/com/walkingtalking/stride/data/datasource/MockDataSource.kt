package com.walkingtalking.stride.data.datasource

import com.walkingtalking.stride.data.dto.LoginResponse

class MockDataSource {
    suspend fun login(type: String, id: String): LoginResponse {
        return LoginResponse("access_token", "refresh_token", false)
    }
}