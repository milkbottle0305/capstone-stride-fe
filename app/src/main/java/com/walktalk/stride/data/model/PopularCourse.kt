package com.walktalk.stride.data.model

data class PopularCourse(
    val courseName: String,
    val participatingCount: Int,
    val nearby: Double,
    val course: List<Coordinate>
)