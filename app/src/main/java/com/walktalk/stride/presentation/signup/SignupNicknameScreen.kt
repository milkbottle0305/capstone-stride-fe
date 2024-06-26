package com.walktalk.stride.presentation.signup

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupNicknameScreen(
    navController: NavController,
    viewModel: SignupViewModel
) {
    val nickname = viewModel.nickname.value
    val isCompleteButtonEnabled = viewModel.isCompleteButtonEnabled.value
    val context = LocalContext.current
    val setUserDataApiState = viewModel.setUserDataApiState.value

    DisposableEffect(setUserDataApiState) {
        when (setUserDataApiState) {
            is ApiState.Success -> {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.SignupNickname.route) {
                        inclusive = true
                    }
                }
            }

            is ApiState.Error -> {
                Log.e("SignupNicknameScreen", "Error: ${setUserDataApiState.message}")
                Toast.makeText(
                    context,
                    "Error: ${setUserDataApiState.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            is ApiState.Loading -> {
            }

            is ApiState.Empty -> {
            }
        }
        onDispose {
            viewModel.resetApiState()
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
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.signup_nickname_guide),
                    modifier = Modifier.align(Alignment.Start),
                    color = StrideTheme.colors.textPrimary,
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
                            color = StrideTheme.colors.textFieldHint
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = StrideTheme.colors.textFieldPrimary,
                        unfocusedContainerColor = StrideTheme.colors.textFieldPrimary,
                        disabledContainerColor = StrideTheme.colors.textFieldPrimary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                Text(
                    stringResource(id = R.string.signup_nickname_policy),
                    color = StrideTheme.colors.textFieldHint,
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
                        viewModel.setUserData()
                    },
                    enabled = isCompleteButtonEnabled && setUserDataApiState !is ApiState.Loading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = StrideTheme.colors.buttonPrimary,
                        contentColor = StrideTheme.colors.buttonTextPrimary,
                        disabledContainerColor = StrideTheme.colors.buttonDisabledPrimary,
                        disabledContentColor = StrideTheme.colors.buttonDisabledTextPrimary,
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
    SignupNicknameScreen(navController = rememberNavController(), viewModel = viewModel())
}