package com.walktalk.stride.data.model

data class PopularCourse(
    val courseName: String,
    val participatingCount: Int,
    val distance: Double,
    val course: List<Coordinate>
)