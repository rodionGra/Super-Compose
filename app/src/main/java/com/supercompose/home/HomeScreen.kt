package com.tutorial.supercompose.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.supercompose.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Button(modifier = Modifier.align(Alignment.Center),
            onClick = {
                navController.navigate(Screen.ComposeThemasScreen.putIndex(0F))
            }) {
            Text(
                text = "ComposeThemasScreen",
                fontSize = 16.sp
            )
        }
    }
}
