package com.walkingtalking.stride.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.walkingtalking.stride.R
import com.walkingtalking.stride.route.Route
import com.walkingtalking.stride.ui.theme.StrideTheme

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = StrideTheme.colors.backgroundPrimary
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
                    color = StrideTheme.colors.textPrimary,
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
                        .clickable { navController.navigate(Route.SignupGenderAge.name) },
                    painter = painterResource(id = R.drawable.apple_login),
                    contentDescription = "apple_login",
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}