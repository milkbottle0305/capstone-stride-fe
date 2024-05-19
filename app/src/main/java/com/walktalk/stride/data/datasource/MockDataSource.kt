package com.walktalk.stride.data.datasource

import com.walktalk.stride.data.dto.request.LoginRequest
import com.walktalk.stride.data.dto.response.LoginResponse
import com.walktalk.stride.data.dto.response.MyRoom
import com.walktalk.stride.data.dto.response.MyRoomsResponse
import com.walktalk.stride.data.dto.response.RecentCourse
import com.walktalk.stride.data.dto.response.RecentCoursesResponse
import com.walktalk.stride.data.dto.response.TodayDoubleData
import com.walktalk.stride.data.dto.response.TodayGoalResponse
import com.walktalk.stride.data.dto.response.TodayIntData
import com.walktalk.stride.data.model.Coordinate

class MockDataSource {
    suspend fun login(request: LoginRequest): LoginResponse {
        return LoginResponse(
            accessToken = "access_token",
            refreshToken = "refresh_token",
            needInitialization = true
        )
    }

    suspend fun getTodayGoal(): TodayGoalResponse {
        return TodayGoalResponse(
            level = 1,
            distance = TodayIntData(3500, 5500),
            speed = TodayDoubleData(4.8, 5.0),
            step = TodayIntData(3000, 5000),
            allComplete = false
        )
    }

    suspend fun getMyRooms(): MyRoomsResponse {
        return MyRoomsResponse(
            results = listOf(
                MyRoom(
                    roomId = 1,
                    roomName = "경희대 국제캠",
                    meetingTime = "Tue, 16 Mar 2021 14:00:00 GMT",
                    courseId = 314,
                    courseName = "경희대 국제캠 한바퀴",
                    participatingCount = 3,
                    course = listOf(
                        Coordinate(23.11231273, 38.12389732),
                        Coordinate(23.11231273, 38.12389732)
                    )
                ),
                MyRoom(
                    roomId = 2,
                    roomName = "경희대 국제캠",
                    meetingTime = "Tue, 16 Mar 2021 14:00:00 GMT",
                    courseId = 315,
                    courseName = "경희대 국제캠 한바퀴",
                    participatingCount = 3,
                    course = listOf(
                        Coordinate(23.11231273, 38.12389732),
                        Coordinate(23.11231273, 38.12389732)
                    )
                )
            )
        )
    }

    suspend fun getRecentCourses(): RecentCoursesResponse {
        return RecentCoursesResponse(
            results = listOf(
                RecentCourse(
                    courseId = 314,
                    doShare = true,
                    courseName = "경희대 국제캠 한바퀴",
                    distance = 3500,
                    time = 74,
                    course = listOf(
                        Coordinate(23.11231273, 38.12389732),
                        Coordinate(23.11231273, 38.12389732)
                    )
                ),
                RecentCourse(
                    courseId = 315,
                    doShare = false,
                    courseName = "",
                    distance = 3500,
                    time = 74,
                    course = listOf(
                        Coordinate(23.11231273, 38.12389732),
                        Coordinate(23.11231273, 38.12389732)
                    )
                )
            ),
            hasNext = true,
            nextCourseId = 316
        )
    }
}