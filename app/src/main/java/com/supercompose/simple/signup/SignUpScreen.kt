package com.supercompose.simple.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TextFieldStateManagerScreen(viewModel: SignUpViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier.align(Alignment.Center),
            value = viewModel.username,
            onValueChange = { username ->
                viewModel.updateUsername(username)
            }
        )

        val userNameHasError by viewModel.userNameHasError.collectAsState()

        if (userNameHasError) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 40.dp),
                text = "FLOW! Username not available. Please choose a different one.",
                color = Color.Red
            )
        }

        if (viewModel.userNameHasLocalError) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp),
                text = "COMPOSE! Username not available. Please choose a different one.",
                color = Color.Red
            )
        }
    }

}


