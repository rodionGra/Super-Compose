package com.supercompose.samples.simple

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


var i = 0

@Composable
fun MyComposable(backPressedDispatcher: OnBackPressedDispatcher) {
    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // do smth
            }
        }
    }
    SideEffect {
        i++ //runs after every recomposition
    }
    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Click me")
    }
}

@Composable
fun CancelableSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }

    rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        it //todo
        var counter by remember {
            mutableStateOf(0)
        }
        if (counter % 5 == 0 && counter > 0) {
            LaunchedEffect(key1 = snackbarHostState.currentSnackbarData) {
                snackbarHostState.showSnackbar("Hello")
            }
        }
        Button(onClick = { counter++ }) {
            SideEffect {
                Log.d(
                    "DEBUG_TAG",
                    "Button recompose is successfully"
                ) //call when recompose is successfully
            }
            Text(text = "Click me $counter")
            Log.d("DEBUG_TAG", "Button")
        }
    }
}

@Composable
fun CancelableSnackbar2() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        it //todo
        val counter = produceState(initialValue = 0) {
            delay(3_000L)
            value = 4
        }
        if (counter.value % 5 == 0 && counter.value > 0) {
            LaunchedEffect(key1 = snackbarHostState.currentSnackbarData) {
                snackbarHostState.showSnackbar("Hello")
            }
        }
        Button(onClick = { }) {
            Text(text = "Click me ${counter.value}")
        }
    }
}

