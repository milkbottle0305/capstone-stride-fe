package com.walktalk.stride.data.repository

import com.walktalk.stride.data.datasource.RemoteDataSource
import com.walktalk.stride.data.dto.request.UserDataRequest

class SignupRepository {
    private val dataSource = RemoteDataSource()

    suspend fun setUserData(request: UserDataRequest): UserDataRequest {
        try {
            return dataSource.setUserData(request)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}