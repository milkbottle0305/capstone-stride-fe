package com.walkingtalking.stride.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignupGenderAge : Screen("signup_gender_age")
    object SignupNickname : Screen("signup_nickname")
    object Main : Screen("main")
    object ExerciseCourseSelection : Screen("exercise_course_selection")
    object Exercise : Screen("exercise")
    object ExerciseSummary : Screen("exercise_summary")
    object Together : Screen("together")
    object RoomList : Screen("room_list")
    object Analysis : Screen("analysis")
}