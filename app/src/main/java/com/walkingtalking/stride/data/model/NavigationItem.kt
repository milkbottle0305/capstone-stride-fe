package com.walkingtalking.stride.data.model

import com.walkingtalking.stride.R
import com.walkingtalking.stride.presentation.navigation.Screen

sealed class NavigationItem(
    val title: Int, val selectIcon: Int, val unselectIcon: Int, val screenRoute: String
) {
    object Walk : NavigationItem(
        R.string.bottom_walk,
        R.drawable.bottom_walk_select,
        R.drawable.bottom_walk_unselect,
        Screen.Main.route
    )

    object Together : NavigationItem(
        R.string.bottom_together,
        R.drawable.bottom_together_select,
        R.drawable.bottom_together_unselect,
        Screen.Together.route
    )

    object Analysis : NavigationItem(
        R.string.bottom_analysis,
        R.drawable.bottom_analysis_select,
        R.drawable.bottom_analysis_unselect,
        Screen.Analysis.route
    )
}