package com.walktalk.stride.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.walktalk.stride.presentation.analysis.AnalysisScreen
import com.walktalk.stride.presentation.exercise.ExerciseScreen
import com.walktalk.stride.presentation.exercise.ExerciseSummaryScreen
import com.walktalk.stride.presentation.exercise.ExerciseViewModel
import com.walktalk.stride.presentation.login.LoginScreen
import com.walktalk.stride.presentation.main.MainScreen
import com.walktalk.stride.presentation.signup.SignupGenderAgeScreen
import com.walktalk.stride.presentation.signup.SignupNicknameScreen
import com.walktalk.stride.presentation.signup.SignupViewModel
import com.walktalk.stride.presentation.together.TogetherScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    lateinit var signupViewModel: SignupViewModel
    lateinit var exerciseViewModel: ExerciseViewModel
            NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController, viewModel = viewModel())
        }
        composable(route = Screen.SignupGenderAge.route) {
            signupViewModel = viewModel()
            SignupGenderAgeScreen(
                navController = navController,
                viewModel = signupViewModel
            )
        }
        composable(route = Screen.SignupNickname.route) {
            SignupNicknameScreen(
                navController = navController,
                viewModel = signupViewModel
            )
        }
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel())
        }
        composable(
            route = Screen.Exercise.route,
            arguments = listOf(navArgument("exerciseType") { type = NavType.StringType })
        ) { entry ->
            val exerciseType = entry.arguments?.getString("exerciseType") ?: "default"
            exerciseViewModel = viewModel()
            ExerciseScreen(
                navController = navController,
                viewModel = exerciseViewModel,
                exerciseType = exerciseType
            )
        }
        composable(route = Screen.ExerciseSummary.route) {
            ExerciseSummaryScreen(navController = navController, viewModel = exerciseViewModel)
        }
        composable(route = Screen.Together.route) {
            TogetherScreen(
                navController = navController
            )
        }
        composable(route = Screen.Analysis.route) {
            AnalysisScreen(
                navController = navController
            )
        }
    }
}