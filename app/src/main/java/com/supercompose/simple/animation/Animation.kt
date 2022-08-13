package com.tutorial.supercompose.simple.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun TweenAnimation() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
        animationSpec = tween(
            durationMillis = 3_000,
            delayMillis = 500,
            easing = LinearEasing
        )
    )
    Box(
        modifier = androidx.compose.ui.Modifier
            .size(size)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text("Increase size")
        }
    }
}

@Composable
@Preview
fun SpringAnimation() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
        )
    )
    Box(
        modifier = androidx.compose.ui.Modifier
            .size(size)
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text("Increase size")
        }
    }
}

@Composable
@Preview
fun KeyFramesAnimation() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
        keyframes {
            durationMillis = 5000
            sizeState at 0 with LinearEasing
            sizeState * 1.5F at 1000 with FastOutLinearInEasing
            sizeState * 2F at 5000
        }
    )
    Box(
        modifier = androidx.compose.ui.Modifier
            .size(size)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text("Increase size")
        }
    }
}

@Composable
@Preview
fun InfiniteColorTransition() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
        tween(
            durationMillis = 1000
        )
    )
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Magenta,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = androidx.compose.ui.Modifier
            .size(size)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text("Increase size")
        }
    }
}
