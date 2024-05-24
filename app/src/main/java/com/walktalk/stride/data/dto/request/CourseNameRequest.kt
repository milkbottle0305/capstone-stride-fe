package com.walktalk.stride.data.dto.request

import com.google.gson.annotations.SerializedName

data class CourseNameRequest(
    @SerializedName("course_name")
    val courseName: String
)
