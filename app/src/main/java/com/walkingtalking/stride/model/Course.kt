package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("course_id") val courseId: Int,
    @SerializedName("do_share") val doShare: Boolean,
    @SerializedName("course_name") val courseName: String,
    val distance: Int,
    val time: Int,
    val course: List<CoursePoint>
)