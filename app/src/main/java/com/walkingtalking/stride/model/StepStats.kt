package com.walkingtalking.stride.model

import com.google.gson.annotations.SerializedName

data class StepStats(
    val mine: List<StepRecord>,
    @SerializedName("age_group") val ageGroup: List<StepRecord>
)

data class StepRecord(
    val step: Int,
    val date: String
)