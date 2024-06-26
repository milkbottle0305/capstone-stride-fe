package com.walktalk.stride.data.api

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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/api/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<RefreshTokenResponse>

    @POST("/api/user")
    suspend fun setUserData(@Body request: UserDataRequest): Response<UserDataRequest>

    @PATCH("/api/user/profile")
    suspend fun updateUserProfile(@Body request: UserProfileRequest): Response<UserProfileRequest>

    @PATCH("/api/user/nickname")
    suspend fun updateUserNickname(@Body request: NicknameRequest): Response<Unit>

    @POST("/api/exercise")
    suspend fun saveExercise(@Body request: ExerciseRequest): Response<Unit>

    @GET("/api/course/mine")
    suspend fun getRecentCourses(
        @Query("show_count") showCount: Int,
        @Query("next_course_id") nextCourseId: Int? = null
    ): Response<RecentCoursesResponse>

    @GET("/api/user/goal")
    suspend fun getTodayGoal(): Response<TodayGoalResponse>
}