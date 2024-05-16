package com.walktalk.stride.data.repository

import com.walktalk.stride.data.datasource.RemoteDataSource
import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.dto.response.LoginResponse

class LoginRepository {
    private val dataSource = RemoteDataSource()
    suspend fun login(request: LoginRequest): LoginResponse = dataSource.login(request)
}