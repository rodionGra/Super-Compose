package com.tutorial.supercompose.simple.animation

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalTextApi::class)
@Composable
fun ShimmerTextAnimationScreen() {
    Box(Modifier.fillMaxSize()) {
        Log.d("DEBUG_TAG", "ShimmerTextAnimationScreen")
        val fontSize by remember {
            mutableStateOf(24.sp)
        }

        val currentFontSizePx = with(LocalDensity.current) { fontSize.toPx() }
        val currentFontSizeDoublePx = currentFontSizePx * 2

        val infiniteTransition = rememberInfiniteTransition()
        val offset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = currentFontSizeDoublePx,
            animationSpec = infiniteRepeatable(tween(200000, easing = LinearEasing))
        )

        val brush = remember(offset) {
            object : ShaderBrush() {
                override fun createShader(size: Size): Shader {
                    val widthOffset = size.width * offset
                    val heightOffset = size.height * offset
                    return LinearGradientShader(
                        colors = listOf(
                            Color.Cyan, Color.Blue,
                            Color.Green, Color.Red,
                            Color.Yellow, Color.Magenta
                        ),
                        from = Offset(widthOffset, heightOffset),
                        to = Offset(widthOffset + size.width, heightOffset + size.height),
                        tileMode = TileMode.Mirror
                    )
                }
            }
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = EXAMPLE_TEXT,
            fontSize = fontSize,
            style = TextStyle(
                brush = brush
            )
        )
    }
}

private val EXAMPLE_TEXT =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
