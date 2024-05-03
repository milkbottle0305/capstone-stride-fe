package com.walkingtalking.stride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.walkingtalking.stride.route.Route
import com.walkingtalking.stride.ui.login.LoginScreen
import com.walkingtalking.stride.ui.signup.SignupGenderAgeScreen
import com.walkingtalking.stride.ui.signup.SignupGenderAgeViewModel
import com.walkingtalking.stride.ui.signup.SignupNicknameScreen
import com.walkingtalking.stride.ui.signup.SignupNicknameViewModel
import com.walkingtalking.stride.ui.theme.StrideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrideTheme {
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Login.name,
    ) {
        composable(route = Route.Login.name) {
            LoginScreen(
                navController = navController
            )
        }
        composable(route = Route.SignupGenderAge.name) {
            val viewModel: SignupGenderAgeViewModel = remember { SignupGenderAgeViewModel() }
            SignupGenderAgeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Route.SignupNickname.name) {
            val viewModel: SignupNicknameViewModel = remember { SignupNicknameViewModel() }
            SignupNicknameScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}