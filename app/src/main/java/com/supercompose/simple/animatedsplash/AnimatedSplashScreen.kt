package com.tutorial.supercompose.simple.animatedsplash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatedSplashScreen() {
    Surface(
        color = Color(0xFF202020),
        modifier = Modifier.fillMaxSize()
    ) {
        Navigation()
    }
}
