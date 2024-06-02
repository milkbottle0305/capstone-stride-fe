@file:OptIn(ExperimentalMaterial3Api::class)

package com.walktalk.stride.presentation.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.walktalk.stride.R
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.presentation.navigation.Screen
import com.walktalk.stride.ui.theme.StrideTheme
import com.walktalk.stride.utils.GoogleApiContract

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    val context = LocalContext.current
    val loginApiState = viewModel.loginApiState.value
    val authResultLauncher = rememberLauncherForActivityResult(
        contract = GoogleApiContract()
    ) { task ->
        viewModel.handleGoogleSignInResult(task)
    }
    LaunchedEffect(loginApiState) {
        when (loginApiState) {
            is ApiState.Success -> {
                when (loginApiState.data) {
                    "로그인 성공: 초기화 필요" -> {
                        navController.navigate(Screen.SignupGenderAge.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }

                    "로그인 성공: 초기화 불필요" -> {
                        navController.navigate(Screen.Main.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            is ApiState.Error -> {
                Toast.makeText(
                    context,
                    "Error: ${loginApiState.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            is ApiState.Loading -> {
            }

            is ApiState.Empty -> {
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = StrideTheme.colors.surface
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 67.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.login_logo),
                    contentDescription = "Login Logo",
                    contentScale = ContentScale.FillWidth,
                )
                Text(
                    modifier = Modifier.padding(
                        top = 13.dp
                    ),
                    text = "거닐다",
                    color = StrideTheme.colors.textSecondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp,
                )
                Image(
                    modifier = Modifier
                        .padding(top = 13.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .clickable { viewModel.kakaoLogin() },
                    painter = painterResource(id = R.drawable.kakao_login),
                    contentDescription = "kakao_login",
                    contentScale = ContentScale.FillWidth,
                )
                Image(
                    modifier = Modifier
                        .padding(top = 13.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .clickable { authResultLauncher.launch(100) },
                    painter = painterResource(id = R.drawable.google_login),
                    contentDescription = "google_login",
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController(), viewModel = viewModel())
}