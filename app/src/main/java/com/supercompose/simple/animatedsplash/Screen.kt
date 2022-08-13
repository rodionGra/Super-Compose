package com.tutorial.supercompose.simple.animatedsplash

sealed class Screen(val name: String) {
    object Splash : Screen("splash_screen")
    object Main : Screen("main_screen")
}
