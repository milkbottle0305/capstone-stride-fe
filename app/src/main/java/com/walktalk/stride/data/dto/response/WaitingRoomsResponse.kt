package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.Coordinate
import com.walktalk.stride.data.model.WaitingRoom

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