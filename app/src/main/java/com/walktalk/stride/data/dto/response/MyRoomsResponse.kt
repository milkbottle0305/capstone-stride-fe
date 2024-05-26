package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.Coordinate

data class MyRoomsResponse(
    val results: List<MyRoom>
)

data class MyRoom(
    @SerializedName("room_id")
    val roomId: Int,
    @SerializedName("room_name")
    val roomName: String,
    @SerializedName("meeting_time")
    val meetingTime: String,
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("course_name")
    val courseName: String,
    @SerializedName("participating_count")
    val participatingCount: Int,
    val course: List<Coordinate>
)

fun MyRoomsResponse.toMyRooms(): List<com.walktalk.stride.data.model.MyRoom> {
    return results.map {
        com.walktalk.stride.data.model.MyRoom(
            roomName = it.roomName,
            courseName = it.courseName,
            participatingCount = it.participatingCount,
            course = it.course.map { coordinate ->
                Coordinate(
                    latitude = coordinate.latitude,
                    longitude = coordinate.longitude
                )
            }
        )
    }
}