package com.walktalk.stride.data.repository

import com.walktalk.stride.data.datasource.MockDataSource
import com.walktalk.stride.data.dto.request.PopularCoursesRequest

class TogetherRepository {
    private val dataSource = MockDataSource()

    suspend fun getPopularCourses(request: PopularCoursesRequest) =
        dataSource.getPopularCourses(request)
}