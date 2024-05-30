package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.Coordinate

data class PopularCoursesResponse(
    val results: List<PopularCourse>,
    @SerializedName("has_next")
    val hasNext: Boolean,
    @SerializedName("next_course_id")
    val nextCourseId: Int
)

data class PopularCourse(
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("course_name")
    val courseName: String,
    val nearby: Int,
    @SerializedName("participating_count")
    val participatingCount: Int,
    val route: List<Coordinate>
)

fun PopularCoursesResponse.toPopularCourses(): List<com.walktalk.stride.data.model.PopularCourse> {
    return results.map {
        com.walktalk.stride.data.model.PopularCourse(
            courseId = it.courseId,
            courseName = it.courseName,
            participatingCount = it.participatingCount,
            nearby = it.nearby.toDouble(),
            course = it.route.map { coordinate ->
                Coordinate(
                    latitude = coordinate.latitude,
                    longitude = coordinate.longitude
                )
            }
        )
    }
}