package com.walktalk.stride.data.dto.request

import com.google.gson.annotations.SerializedName

data class StatsRequest(
    val type: String,
    @SerializedName("show_count")
    val showCount: Int,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String
)
