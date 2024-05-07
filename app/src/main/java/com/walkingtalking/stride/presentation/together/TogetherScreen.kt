@file:OptIn(ExperimentalMaterial3Api::class)

package com.walkingtalking.stride.presentation.together

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.walkingtalking.stride.presentation.components.StrideNavigationBar
import com.walkingtalking.stride.ui.theme.StrideTheme

@Composable
fun TogetherScreen(navController: NavController) {
    Scaffold(
        bottomBar = { StrideNavigationBar(navController = navController) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = StrideTheme.colors.backgroundPrimary,
        ) {
        }
    }
}

@Preview
@Composable
fun TogetherScreenPreview() {
    val navController = rememberNavController()
    TogetherScreen(navController)
}