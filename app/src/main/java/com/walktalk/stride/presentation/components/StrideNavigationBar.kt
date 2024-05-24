package com.walktalk.stride.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.walktalk.stride.data.model.NavigationItem
import com.walktalk.stride.ui.theme.StrideTheme

@Composable
fun StrideNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem.Together,
        NavigationItem.Walk,
        NavigationItem.Analysis
    )
    NavigationBar(
        modifier = Modifier,
        containerColor = StrideTheme.colors.navBackground
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.screenRoute,
                onClick = {
                    navController.navigate(navItem.screenRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = navItem.selectIcon),
                        contentDescription = "navigation icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = StrideTheme.colors.navSelected,
                    unselectedIconColor = StrideTheme.colors.navUnselected,
                    indicatorColor = StrideTheme.colors.navBackground,
                )
            )
        }
    }
}