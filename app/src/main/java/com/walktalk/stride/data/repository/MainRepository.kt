package com.walktalk.stride.data.repository

import com.walktalk.stride.data.datasource.MockDataSource
import com.walktalk.stride.data.datasource.RemoteDataSource

class MainRepository {
    private val mockDataSource = MockDataSource()
    private val remoteDataSource = RemoteDataSource()

    suspend fun getTodayGoal() = remoteDataSource.getTodayGoal()

    suspend fun getMyRooms() = mockDataSource.getMyRooms()

    suspend fun getRecentCourses(showCount: Int, nextCourseId: Int? = null) =
        remoteDataSource.getRecentCourses(showCount, nextCourseId)
}