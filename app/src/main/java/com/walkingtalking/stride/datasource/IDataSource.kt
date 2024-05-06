package com.walkingtalking.stride.datasource

import com.walkingtalking.stride.model.CourseResponse
import com.walkingtalking.stride.model.Exercise
import com.walkingtalking.stride.model.Goal
import com.walkingtalking.stride.model.LoginResponse
import com.walkingtalking.stride.model.ParticipatingRoomResponse
import com.walkingtalking.stride.model.RefreshResponse
import com.walkingtalking.stride.model.SpeedStats
import com.walkingtalking.stride.model.StepStats
import com.walkingtalking.stride.model.StrideStats
import com.walkingtalking.stride.model.WaitingRoomResponse

interface IDataSource {
    suspend fun login(type: String, id: String): LoginResponse
    suspend fun refreshToken(refreshToken: String): RefreshResponse
    suspend fun setUserData(gender: String, age: String, nickname: String): Boolean
    suspend fun updateUserData(gender: String, age: String): Boolean
    suspend fun updateNickname(nickname: String): Boolean
    suspend fun getGoal(): Goal
    suspend fun saveExercise(exercise: Exercise): Boolean
    suspend fun getStrideStats(): StrideStats
    suspend fun getSpeedStats(): SpeedStats
    suspend fun getStepStats(): StepStats
    suspend fun getMyRecentCourses(): CourseResponse
    suspend fun getRecommendedCourses(): CourseResponse
    suspend fun getPopularCourses(): CourseResponse
    suspend fun updateMyCourseName(courseName: String): Boolean
    suspend fun getWaitingRooms(): WaitingRoomResponse
    suspend fun createRoom(roomName: String, meetingTime: String): Boolean
    suspend fun getParticipatingRooms(): ParticipatingRoomResponse
    suspend fun enterRoom(roomId: Int): Boolean
    suspend fun leaveRoom(roomId: Int): Boolean
}