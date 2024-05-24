package com.walktalk.stride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.walktalk.stride.presentation.navigation.NavGraph
import com.walktalk.stride.ui.theme.StrideTheme
import com.walktalk.stride.utils.LoginUtils

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrideTheme {
                navController = rememberNavController()

                var startDestination by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(true) {
                    startDestination =
                        LoginUtils.autoLogin(
                            context = this@MainActivity,
                        )
                }

                startDestination?.let {
                    NavGraph(navController = navController, startDestination = it)
                }
            }
        }
    }
}