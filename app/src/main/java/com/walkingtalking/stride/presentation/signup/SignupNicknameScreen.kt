package com.walkingtalking.stride.presentation.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.walkingtalking.stride.R
import com.walkingtalking.stride.presentation.navigation.Screen
import com.walkingtalking.stride.ui.theme.StrideTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupNicknameScreen(
    navController: NavController,
    viewModel: SignupViewModel
) {
    val nickname = viewModel.nickname.value
    val isCompleteButtonEnabled = viewModel.isCompleteButtonEnabled.value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = StrideTheme.colors.backgroundPrimary
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.signup_nickname_guide),
                    modifier = Modifier.align(Alignment.Start),
                    color = StrideTheme.colors.textSecondary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(
                    modifier = Modifier.height(33.dp)
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = nickname,
                    singleLine = true,
                    onValueChange = {
                        if (it.length <= 8)
                            viewModel.setNickname(it)
                    },
                    placeholder = {
                        Text(
                            stringResource(id = R.string.signup_nickname_hint),
                            color = StrideTheme.colors.hint,
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = StrideTheme.colors.backgroundSecondary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                Text(
                    stringResource(id = R.string.signup_nickname_policy),
                    color = StrideTheme.colors.hint,
                    fontSize = 16.sp,
                )
                Spacer(
                    modifier = Modifier.height(32.dp)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    onClick = {
                        navController.navigate(
                            Screen.Main.route,
                            builder = { popUpTo(Screen.Login.route) { inclusive = true } }
                        )
                    },
                    enabled = isCompleteButtonEnabled,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = StrideTheme.colors.buttonPrimary,
                        contentColor = StrideTheme.colors.buttonTextPrimary,
                        disabledContainerColor = StrideTheme.colors.disableButtonPrimary,
                        disabledContentColor = StrideTheme.colors.disableButtonTextPrimary,
                    ),
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = stringResource(id = R.string.signup_complete)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignupNicknameScreen() {
    val navController = rememberNavController()
    val viewModel = SignupViewModel()
    SignupNicknameScreen(navController = navController, viewModel = viewModel)
}