@file:OptIn(ExperimentalMaterial3Api::class)

package com.walktalk.stride.presentation.together

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.walktalk.stride.R
import com.walktalk.stride.data.model.ApiState
import com.walktalk.stride.data.model.PopularCourse
import com.walktalk.stride.presentation.components.StrideNavigationBar
import com.walktalk.stride.presentation.together.components.CourseCard
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun TogetherScreen(navController: NavController, viewModel: TogetherViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getPopularCourses()
    }
    when (viewModel.popularCoursesApiState.value) {
        is ApiState.Loading -> {
            // Show loading indicator
        }

        is ApiState.Success -> {
            TogetherContent(
                navController = navController,
                popularCourses = viewModel.popularCourses
            )
        }

        is ApiState.Error -> {
            // Show error message
        }

        is ApiState.Empty -> {
            // Show empty indicator
        }
    }
}

@Composable
fun TogetherContent(navController: NavController, popularCourses: List<PopularCourse>) {
    Scaffold(
        bottomBar = { StrideNavigationBar(navController = navController) },
        containerColor = StrideTheme.colors.surface,
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues),
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                ),
            ) {
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = stringResource(id = R.string.together_title),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = StrideTheme.colors.textSecondary
                )
                Spacer(modifier = Modifier.padding(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    items(popularCourses.size) { itemIndex ->
                        CourseCard(
                            pathList = popularCourses[itemIndex].course,
                            courseName = popularCourses[itemIndex].courseName,
                            participatingCount = popularCourses[itemIndex].participatingCount,
                            nearby = popularCourses[itemIndex].nearby
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TogetherContentPreview() {
}