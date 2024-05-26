package com.walktalk.stride.data.model

data class RecentCourse(
    val courseName: String,
    val distance: Int,
    val time: String,
    val course: List<Coordinate>
)