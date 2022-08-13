package com.tutorial.supercompose.simple.animatedsplash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.supercompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val scale = remember {
        Animatable(0F)
    }
    LaunchedEffect(key1 = true, block = {
        delay(1_000L)
        scale.animateTo(
            targetValue = 1F,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2F).getInterpolation(it)
                }
            )
        )
        delay(3_000L)
        navController.navigate(Screen.Main.name)
    })
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.scale(scale.value),
            painter = painterResource(id = R.drawable.hacker),
            contentDescription = "Logo"
        )
    }
}
