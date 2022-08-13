package com.supercompose.simple.draggable

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.supercompose.R
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@Composable
fun DraggableMusicKnobScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var volume by remember {
                mutableStateOf(0F)
            }
            val barCount = 20
            MusicKnob(
                modifier = Modifier.size(100.dp)
            ) {
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))
            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )
        }
    }
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 0
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2F * barCount)
        }

        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2F + barWidth / 2F, 0F),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0F)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25F,
    onValuerChange: (Float) -> Unit = {}
) {
    var rotation by remember {
        mutableStateOf(limitAngle)
    }
    var touchX by remember {
        mutableStateOf(0F)
    }
    var touchY by remember {
        mutableStateOf(0F)
    }
    var centerX by remember {
        mutableStateOf(0F)
    }
    var centerY by remember {
        mutableStateOf(0F)
    }

    Image(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBoundsSize = it.boundsInWindow().size
                centerX = windowBoundsSize.width / 2F
                centerY = windowBoundsSize.height / 2F
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180 / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitAngle..limitAngle) {
                            val fixedAngle = if (angle in -180F..-limitAngle) {
                                360 + angle
                            } else angle
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitAngle) / (360F - 2 * limitAngle)
                            onValuerChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation),
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "music_knob"
    )
}

