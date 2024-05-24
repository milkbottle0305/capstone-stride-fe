package com.walktalk.stride.data.repository

import com.walktalk.stride.data.datasource.RemoteDataSource
import com.walktalk.stride.data.dto.request.ExerciseRequest

class ExerciseRepository {
    private val dataSource = RemoteDataSource()
    suspend fun saveExercise(request: ExerciseRequest): Boolean = dataSource.saveExercise(request)

}