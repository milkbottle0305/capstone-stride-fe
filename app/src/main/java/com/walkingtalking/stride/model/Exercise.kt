package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class Exercise(
    @SerializedName("min_stride") val minStride: Int,
    @SerializedName("max_stride") val maxStride: Int,
    @SerializedName("average_stride") val averageStride: Int,
    @SerializedName("min_speed") val minSpeed: Double,
    @SerializedName("max_speed") val maxSpeed: Double,
    @SerializedName("average_speed") val averageSpeed: Double,
    val step: Int,
    val distance: Int,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    val stability: Double,
    val course: List<CoursePoint>,
    @SerializedName("do_share_course") val doShareCourse: Boolean,
    @SerializedName("course_name") val courseName: String
)