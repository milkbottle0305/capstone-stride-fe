package com.walkingtalking.stride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.walkingtalking.stride.presentation.navigation.NavGraph
import com.walkingtalking.stride.ui.theme.StrideTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrideTheme {
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}