package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class WaitingRoom(
    @SerializedName("room_id") val roomId: Int,
    @SerializedName("meeting_time") val meetingTime: String,
    @SerializedName("participating_count") val participatingCount: Int
)
