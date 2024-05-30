package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.Coordinate
import com.walktalk.stride.data.model.WaitingRoom
import com.walktalk.stride.data.model.WaitingRooms

data class WaitingRoomsResponse(
    val results: List<WaitingRoom>,
    @SerializedName("course_name")
    val courseName: String,
    val course: List<Coordinate>
)

data class WaitingRoom(
    @SerializedName("room_id")
    val roomId: Int,
    @SerializedName("meeting_time")
    val meetingTime: String,
    @SerializedName("participating_count")
    val participatingCount: Int
)

fun WaitingRoomsResponse.toWaitingRooms(): WaitingRooms {
    return WaitingRooms(
        courseName = courseName,
        rooms = results.map {
            WaitingRoom(
                roomId = it.roomId,
                meetingTime = it.meetingTime,
                participatingCount = it.participatingCount
            )
        },
        course = course.map { coordinate ->
            Coordinate(
                latitude = coordinate.latitude,
                longitude = coordinate.longitude
            )
        },
    )
}