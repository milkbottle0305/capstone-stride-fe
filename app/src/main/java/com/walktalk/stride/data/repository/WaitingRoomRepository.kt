package com.walktalk.stride.data.repository

import com.walktalk.stride.data.datasource.MockDataSource

class WaitingRoomRepository {
    private val dataSource = MockDataSource()

    suspend fun getWaitingRooms(courseId: Int) = dataSource.getWaitingRooms(courseId)
}