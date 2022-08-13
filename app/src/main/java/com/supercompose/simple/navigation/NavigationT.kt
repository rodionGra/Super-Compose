package com.tutorial.supercompose.simple.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

const val DETAIL_SCREEN_PARAMETER_SCREEN = "name"

@Composable
fun NavigationTheme() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenT.MainScreenT.route
    ) {
        composable(route = ScreenT.MainScreenT.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = ScreenT.DetailScreenT.route + "/{$DETAIL_SCREEN_PARAMETER_SCREEN}",
            arguments = listOf(
                navArgument(DETAIL_SCREEN_PARAMETER_SCREEN) {
                    type = NavType.StringType
                    defaultValue = "Default"
                    nullable = true
                }
            )
        ) { entry ->
            DetailScreen(name = entry.arguments?.getString(DETAIL_SCREEN_PARAMETER_SCREEN))
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Button(
            onClick = {
                navController.navigate(
                    ScreenT.DetailScreenT.withArgs(text)
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Go to detail screen")
        }
    }
}

@Composable
fun DetailScreen(name: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
