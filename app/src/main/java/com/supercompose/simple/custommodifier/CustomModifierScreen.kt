package com.supercompose.simple.custommodifier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomModifierScreen() {
    Box(Modifier.fillMaxSize()) {
        InfoLabels {
            Text(
                text = "City:",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(InfoAlignment.Center),
            )
            Text(
                text = "Vancouver",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(InfoAlignment.Center),
            )
            Text(
                text = "Country:",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(InfoAlignment.Center),
            )
            Text(
                text = "Canada",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(InfoAlignment.Center),
            )
            Text(
                text = "Country code:",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(InfoAlignment.Center),
            )
            Text(
                text = "CA",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(InfoAlignment.Center),
            )
        }
    }
}




