package com.walktalk.stride.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.walktalk.stride.presentation.analysis.AnalysisScreen
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
                viewModel = viewModel()
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