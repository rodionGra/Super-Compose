package com.tutorial.supercompose.simple.bottomnavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = ScreenBottomNavigation.Home.name) {
        composable(ScreenBottomNavigation.Home.name) {
            HomeScreen()
        }
        composable(ScreenBottomNavigation.Chat.name) {
            ChatScreen()
        }
        composable(ScreenBottomNavigation.Settings.name) {
            SettingsScreen()
        }
    }
}


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings screen")
    }
}

sealed class ScreenBottomNavigation(val name: String) {
    object Home : ScreenBottomNavigation("home")
    object Chat : ScreenBottomNavigation("chat")
    object Settings : ScreenBottomNavigation("settings")
}
