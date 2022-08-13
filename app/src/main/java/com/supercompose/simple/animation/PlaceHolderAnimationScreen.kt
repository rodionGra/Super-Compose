@file:OptIn(ExperimentalAnimationApi::class)

package com.tutorial.supercompose.simple.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun PlaceholderAnimationScreen() {
    Box(Modifier.fillMaxSize()) {
        var currentQuery by remember {
            mutableStateOf("")
        }
        SearchBar(
            modifier = Modifier.align(Alignment.Center),
            query = currentQuery,
            onQueryChange = {
                currentQuery = it
            },
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            AnimationPlaceholder(
                hints = listOf(
                    "Search your favorite food",
                    "Search your favorite youtube channel",
                    "Search your favorite topic"
                )
            )
        }
    )
}

@Composable
fun AnimationPlaceholder(
    hints: List<String>,
) {
    val iterator = hints.listIterator()

    val target by produceState(initialValue = hints.first()) {
        iterator.doWhenHasNextOrPrevious(1_000L) {
            value = it
        }
    }

    AnimatedContent(
        targetState = target,
        transitionSpec = { ScrollAnimation() }
    ) { str ->
        Text(text = str, fontSize = 14.sp)
    }
}

suspend fun <T> ListIterator<T>.doWhenHasNextOrPrevious(
    delayMillis: Long,
    doWork: suspend (T) -> Unit
) {
    while (hasPrevious() || hasNext()) {
        while (hasPrevious()) {
            delay(delayMillis)
            doWork.invoke(previous())
        }
        while (hasNext()) {
            delay(delayMillis)
            doWork.invoke(next())
        }
    }
}

object ScrollAnimation {
    operator fun invoke(): ContentTransform {
        return slideInVertically(
            initialOffsetY = { 50 },
            animationSpec = tween()
        ) + fadeIn() with slideOutVertically(
            targetOffsetY = { -50 },
            animationSpec = tween()
        )
    }
}
