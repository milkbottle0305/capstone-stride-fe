package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class ParticipatingRoom(
    @SerializedName("room_id") val roomId: Int,
    @SerializedName("room_name") val roomName: String,
    @SerializedName("meeting_time") val meetingTime: String,
    @SerializedName("course_id") val courseId: Int,
    @SerializedName("course_name") val courseName: String,
    @SerializedName("participating_count") val participatingCount: Int,
    val course: List<CoursePoint>
)