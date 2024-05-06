package com.walkingtalking.stride.data.repository

import com.walkingtalking.stride.data.datasource.RemoteDataSource
import com.walkingtalking.stride.data.dto.LoginResponse

class LoginRepository(private val dataSource: RemoteDataSource) {
    suspend fun login(type: String, id: String): LoginResponse {
        return dataSource.login(type, id)
    }
}