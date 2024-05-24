package com.walktalk.stride.presentation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen() {
    CircularProgressIndicator()
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}