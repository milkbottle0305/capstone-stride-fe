package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName
import com.walktalk.stride.data.model.Coordinate

data class RecentCoursesResponse(
    val results: List<RecentCourse>,
    @SerializedName("has_next")
    val hasNext: Boolean,
    @SerializedName("next_course_id")
    val nextCourseId: Int
)

data class RecentCourse(
    @SerializedName("course_id")
    val courseId: Int,
    @SerializedName("do_share")
    val doShare: Boolean,
    @SerializedName("course_name")
    val courseName: String,
    val distance: Int,
    val time: Int,
    val course: List<Coordinate>
)

fun RecentCoursesResponse.toRecentCourses(): List<com.walktalk.stride.data.model.RecentCourse> {
    return results.map {
        com.walktalk.stride.data.model.RecentCourse(
            courseName = it.courseName,
            distance = it.distance,
            time = "${it.time / 60}시간 ${it.time % 60}분",
            course = it.course.map { coordinate ->
                Coordinate(
                    latitude = coordinate.latitude,
                    longitude = coordinate.longitude
                )
            }
        )
    }
}