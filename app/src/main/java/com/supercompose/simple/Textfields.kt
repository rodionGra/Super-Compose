package com.tutorial.supercompose.simple

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ShowTextSnackBar(text: String) {
    Snackbar {
        Text(text)
    }
}

@Composable
fun ShowTextScaffold() {
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldState,
                label = {
                    Text("Enter your name")
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch { scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState") }
            }) {
                Text("Plz greet me")
            }
        }
    }
}