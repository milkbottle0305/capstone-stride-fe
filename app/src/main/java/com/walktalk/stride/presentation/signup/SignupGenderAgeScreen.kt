package com.walktalk.stride.presentation.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.walktalk.stride.R
import com.walktalk.stride.presentation.navigation.Screen
import com.walktalk.stride.ui.theme.StrideTheme.colors

@Composable
fun SignupGenderAgeScreen(
    navController: NavController,
    viewModel: SignupViewModel
) {
    val genderIndex = viewModel.genderIndex.value
    val ageIndex = viewModel.ageIndex.value
    val isNextButtonEnabled = viewModel.isNextButtonEnabled.value

    val genders = listOf(
        stringResource(R.string.signup_gender_male),
        stringResource(R.string.signup_gender_female)
    )

    val ageRanges = listOf(
        stringResource(R.string.signup_age_less_50),
        stringResource(R.string.signup_age_50_54),
        stringResource(R.string.signup_age_55_59),
        stringResource(R.string.signup_age_60_69),
        stringResource(R.string.signup_age_70_79),
        stringResource(R.string.signup_age_more_80)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colors.surface
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(R.string.signup_gender_age_guide),
                    color = colors.textPrimary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = stringResource(R.string.signup_policy),
                    color = colors.textPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(
                    modifier = Modifier.height(33.dp)
                )
                Text(
                    text = stringResource(R.string.signup_gender_guide),
                    color = colors.textPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(60.dp)
                    ) {
                        repeat(genders.size) {
                            Box(
                                modifier = Modifier
                                    .width(130.dp)
                                    .height(130.dp)
                                    .background(
                                        color = if (genderIndex == it) colors.buttonPrimary else colors.buttonSecondary,
                                        shape = CircleShape
                                    )
                                    .border(
                                        1.dp,
                                        color = if (genderIndex == it) colors.buttonBorderPrimary else colors.buttonBorderSecondary,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .clickable { viewModel.onGenderSelected(it) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = genders[it],
                                    color = if (genderIndex == it) colors.buttonTextPrimary else colors.buttonTextSecondary,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(44.dp)
                )
                Text(
                    text = stringResource(R.string.signup_age_guide),
                    color = colors.textPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(
                    modifier = Modifier.height(26.dp)
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(25.dp),
                        verticalArrangement = Arrangement.spacedBy(33.dp),
                    ) {
                        items(6) {
                            Surface(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable { viewModel.onAgeRangeSelected(it) },
                                shape = RoundedCornerShape(16.dp),
                                border = BorderStroke(
                                    1.dp,
                                    if (ageIndex == it) colors.buttonBorderPrimary else colors.buttonBorderSecondary,
                                ),
                                color = if (ageIndex == it) colors.buttonPrimary else colors.buttonSecondary,
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = ageRanges[it],
                                        color = if (ageIndex == it) colors.buttonTextPrimary else colors.buttonTextSecondary,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    enabled = isNextButtonEnabled,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.buttonPrimary,
                        contentColor = colors.buttonTextPrimary,
                        disabledContainerColor = colors.buttonDisabledPrimary,
                        disabledContentColor = colors.buttonDisabledTextPrimary,
                    ),
                    onClick = {
                        navController.navigate(Screen.SignupNickname.route)
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = stringResource(R.string.signup_next),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignupGenderAgeScreen() {
    SignupGenderAgeScreen(navController = rememberNavController(), viewModel = viewModel())
}