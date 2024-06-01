package com.walktalk.stride.data.model

data class WaitingRooms(
    val courseName: String,
    val rooms: List<WaitingRoom>,
    val course: List<Coordinate>
)

data class WaitingRoom(
    val roomId: Int,
    val meetingTime: String,
    val participatingCount: Int
)