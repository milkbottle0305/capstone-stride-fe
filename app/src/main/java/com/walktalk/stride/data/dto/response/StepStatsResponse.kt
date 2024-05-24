package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName

data class StepStatsResponse(
    val mine: List<StepSummary>,
    @SerializedName("age_group")
    val ageGroup: List<StepSummary>
)

data class StepSummary(
    val step: Int,
    val date: String
)