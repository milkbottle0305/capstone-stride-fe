package com.walktalk.stride.data.dto.request

import com.google.gson.annotations.SerializedName

data class PopularCoursesRequest(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("show_count")
    val showCount: Int,
    @SerializedName("next_course_id")
    val nextCourseId: Int,
)
