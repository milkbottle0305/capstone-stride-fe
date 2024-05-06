package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class WaitingRoomResponse(
    val results: List<WaitingRoom>,
    @SerializedName("course_name") val courseName: String,
    val course: List<CoursePoint>
)