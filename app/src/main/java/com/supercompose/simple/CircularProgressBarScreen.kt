package com.tutorial.supercompose.simple

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularProgressBarScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedCircularProgressBar(percentage = 0.8F, number = 100)
    }
}

@Composable
fun AnimatedCircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var isAnimationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (isAnimationPlayed) percentage else 0F,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = Unit) {
        launch {
            delay(3_000L)
            isAnimationPlayed = true
        }
    }

    Box(
        modifier = Modifier.size(radius * 2F),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2F)) {
            drawArc(
                color = color,
                startAngle = -90F,
                sweepAngle = 360F * curPercentage.value,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = (curPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
