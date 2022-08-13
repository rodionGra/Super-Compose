package com.tutorial.supercompose.simple.animatedsplash

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.name
    ) {
        composable(Screen.Splash.name) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Main.name) {
            MainScreen()
        }
    }
}
