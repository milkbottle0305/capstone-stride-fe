package com.walktalk.stride.data.dto.request

import com.google.gson.annotations.SerializedName

data class RoomRequest(
    @SerializedName("room_name")
    val roomName: String,
    @SerializedName("meeting_time")
    val meetingTime: String,
)
