package com.walktalk.stride.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignupGenderAge : Screen("gender_age")
    object SignupNickname : Screen("nickname")
    object Main : Screen("main")
    object Exercise : Screen("exercise?exerciseType={exerciseType}&goalStep={goalStep}")
    object ExerciseSummary : Screen("exercise_summary")
    object Together : Screen("together")
    object WaitingRoom : Screen("waiting_room?courseId={courseId}")
    object Analysis : Screen("analysis")
}