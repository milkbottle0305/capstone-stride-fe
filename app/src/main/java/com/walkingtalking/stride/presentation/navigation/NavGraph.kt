package com.walkingtalking.stride.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.walkingtalking.stride.presentation.analysis.AnalysisScreen
import com.walkingtalking.stride.presentation.login.LoginScreen
import com.walkingtalking.stride.presentation.main.MainScreen
import com.walkingtalking.stride.presentation.signup.SignupGenderAgeScreen
import com.walkingtalking.stride.presentation.signup.SignupNicknameScreen
import com.walkingtalking.stride.presentation.signup.SignupViewModel
import com.walkingtalking.stride.presentation.together.TogetherScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignupGenderAge.route) {
            val viewModel: SignupViewModel = viewModel()
            SignupGenderAgeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Screen.SignupGenderAge.route) {
            val viewModel: SignupViewModel = viewModel()
            SignupNicknameScreen(
                navController = navController,
                viewModel = viewModel()
            )
        }
        composable(route = Screen.Main.route) {
            MainScreen(
                navController = navController
            )
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