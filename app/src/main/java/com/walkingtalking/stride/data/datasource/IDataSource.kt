package com.walkingtalking.stride.data.datasource

import com.walkingtalking.stride.data.dto.LoginResponse

interface IDataSource {
    suspend fun login(type: String, id: String): LoginResponse
}