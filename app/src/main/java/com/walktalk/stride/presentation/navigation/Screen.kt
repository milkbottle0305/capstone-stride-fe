package com.walktalk.stride.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignupGenderAge : Screen("gender_age")
    object SignupNickname : Screen("nickname")
    object Main : Screen("main")
    object ExerciseCourseSelection : Screen("exercise_course_selection")
    object Exercise : Screen("exercise/{exerciseType}")
    object ExerciseSummary : Screen("exercise_summary")
    object Together : Screen("together")
    object RoomList : Screen("room_list")
    object Analysis : Screen("analysis")
}