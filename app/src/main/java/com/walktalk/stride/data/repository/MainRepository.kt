package com.walktalk.stride.data.repository

import com.walktalk.stride.data.datasource.MockDataSource

class MainRepository {
    private val dataSource = MockDataSource()

    suspend fun getTodayGoal() = dataSource.getTodayGoal()

    suspend fun getMyRooms() = dataSource.getMyRooms()

    suspend fun getRecentCourses() = dataSource.getRecentCourses()
}