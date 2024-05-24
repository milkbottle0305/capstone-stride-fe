package com.walktalk.stride.data.dto.response

import com.google.gson.annotations.SerializedName

data class StrideStatsResponse(
    val mine: List<StrideSummary>,
    @SerializedName("age_group")
    val ageGroup: List<StrideSummary>
)

data class StrideSummary(
    @SerializedName("min_stride")
    val minStride: Int,
    @SerializedName("max_stride")
    val maxStride: Int,
    @SerializedName("average_stride")
    val averageStride: Int,
    val stability: Double,
    val date: String
)