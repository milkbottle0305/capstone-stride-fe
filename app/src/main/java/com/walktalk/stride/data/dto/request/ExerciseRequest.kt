package com.walktalk.stride.data.dto.request

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.Coordinate

data class ExerciseRequest(
    @SerializedName("min_stride")
    val minStride: Int,
    @SerializedName("max_stride")
    val maxStride: Int,
    @SerializedName("average_stride")
    val averageStride: Int,
    @SerializedName("min_speed")
    val minSpeed: Double,
    @SerializedName("max_speed")
    val maxSpeed: Double,
    @SerializedName("average_speed")
    val averageSpeed: Double,
    @SerializedName("data_count")
    val dataCount: Int,
    val step: Int,
    val distance: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    val stability: Double,
    val course: List<Coordinate>,
    @SerializedName("do_share_course")
    val doShareCourse: Boolean,
    @SerializedName("course_name")
    val courseName: String
)