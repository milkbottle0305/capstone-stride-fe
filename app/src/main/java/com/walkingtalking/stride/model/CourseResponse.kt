package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    val results: List<Course>,
    @SerializedName("has_next") val hasNext: Boolean,
    @SerializedName("next_course_id") val nextCourseId: Int
)