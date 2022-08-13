package com.tutorial.supercompose.simple.bottomnavigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavigationWithBadgesScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navItemsList = listOf(
                    BottomNavItem("Home", ScreenBottomNavigation.Home.name, Icons.Default.Home),
                    BottomNavItem(
                        "Chat",
                        ScreenBottomNavigation.Chat.name,
                        Icons.Default.Notifications,
                        23
                    ),
                    BottomNavItem(
                        "Settings",
                        ScreenBottomNavigation.Settings.name,
                        Icons.Default.Settings
                    ),
                ),
                navController = navController,
                modifier = Modifier,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        BottomNavigation(navController = navController)
    }
}
