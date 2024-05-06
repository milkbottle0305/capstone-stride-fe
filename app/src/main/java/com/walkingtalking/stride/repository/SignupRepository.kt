package com.walkingtalking.stride.repository

import com.walkingtalking.stride.datasource.IDataSource

class SignupRepository(private val dataSource: IDataSource) {
    suspend fun login(type: String, id: String) = dataSource.login(type, id)
    suspend fun refreshToken(refreshToken: String) = dataSource.refreshToken(refreshToken)
}