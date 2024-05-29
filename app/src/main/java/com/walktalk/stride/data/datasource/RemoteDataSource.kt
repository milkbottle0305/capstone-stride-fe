package com.walktalk.stride.data.datasource

import com.walktalk.stride.data.api.ApiService
import com.walktalk.stride.data.api.RetrofitInstance
import com.walktalk.stride.data.dto.request.ExerciseRequest
import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.dto.request.NicknameRequest
import com.walktalk.stride.data.dto.request.RefreshTokenRequest
import com.walktalk.stride.data.dto.request.UserDataRequest
import com.walktalk.stride.data.dto.request.UserProfileRequest
import com.walktalk.stride.data.dto.response.LoginResponse
import com.walktalk.stride.data.dto.response.RecentCoursesResponse
import com.walktalk.stride.data.dto.response.RefreshTokenResponse
import com.walktalk.stride.data.dto.response.TodayGoalResponse

class RemoteDataSource {
    private val apiService: ApiService = RetrofitInstance.apiService

    suspend fun login(request: LoginRequest): LoginResponse {
        try {
            val response = apiService.login(request)
            if (response.isSuccessful)
                return response.body()!!
            else throw Exception(response.errorBody().toString())
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    suspend fun setUserData(request: UserDataRequest): UserDataRequest {
        try {
            val response = apiService.setUserData(request)
            if (response.isSuccessful)
                return response.body()!!
            else throw Exception(response.errorBody().toString())
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    suspend fun updateUserProfile(request: UserProfileRequest): Boolean {
        return try {
            val response = apiService.updateUserProfile(request)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateUserNickname(request: NicknameRequest): Boolean {
        return try {
            val response = apiService.updateUserNickname(request)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun refreshToken(request: RefreshTokenRequest): RefreshTokenResponse {
        try {
            val response = apiService.refreshToken(request)
            if (response.isSuccessful)
                return response.body()!!
            else throw Exception(response.errorBody().toString())
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    suspend fun saveExercise(request: ExerciseRequest): Boolean {
        return try {
            val response = apiService.saveExercise(request)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getTodayGoal(): TodayGoalResponse {
        return try {
            val response = apiService.getTodayGoal()
            if (response.isSuccessful)
                response.body()!!
            else throw Exception(response.errorBody().toString())
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    suspend fun getRecentCourses(showCount: Int, nextCourseId: Int? = null): RecentCoursesResponse {
        return try {
            val response = apiService.getRecentCourses(showCount, nextCourseId)
            if (response.isSuccessful)
                response.body()!!
            else throw Exception(response.errorBody().toString())
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}