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

class ApiDataSource : IDataSource {
    override suspend fun login(type: String, id: String): LoginResponse {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(refreshToken: String): RefreshResponse {
        TODO("Not yet implemented")
    }

    override suspend fun setUserData(gender: String, age: String, nickname: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserData(gender: String, age: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateNickname(nickname: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getGoal(): Goal {
        TODO("Not yet implemented")
    }

    override suspend fun saveExercise(exercise: Exercise): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getStrideStats(): StrideStats {
        TODO("Not yet implemented")
    }

    override suspend fun getSpeedStats(): SpeedStats {
        TODO("Not yet implemented")
    }

    override suspend fun getStepStats(): StepStats {
        TODO("Not yet implemented")
    }

    override suspend fun getMyRecentCourses(): CourseResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommendedCourses(): CourseResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularCourses(): CourseResponse {
        TODO("Not yet implemented")
    }

    override suspend fun updateMyCourseName(courseName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getWaitingRooms(): WaitingRoomResponse {
        TODO("Not yet implemented")
    }

    override suspend fun createRoom(roomName: String, meetingTime: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getParticipatingRooms(): ParticipatingRoomResponse {
        TODO("Not yet implemented")
    }

    override suspend fun enterRoom(roomId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun leaveRoom(roomId: Int): Boolean {
        TODO("Not yet implemented")
    }
}