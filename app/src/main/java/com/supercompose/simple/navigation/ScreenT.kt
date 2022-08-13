package com.tutorial.supercompose.simple.navigation

sealed class ScreenT(val route: String) {
    object MainScreenT : ScreenT("main_screen")
    object DetailScreenT : ScreenT("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}