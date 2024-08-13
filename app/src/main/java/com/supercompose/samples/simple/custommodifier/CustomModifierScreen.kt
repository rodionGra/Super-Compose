package com.supercompose.samples.simple.custommodifier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomModifierScreen() {
    Box(Modifier.fillMaxSize()) {
        InfoLabels {
            Text(
                text = "City:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(InfoAlignment.Center),
            )
            Text(
                text = "Vancouver",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(InfoAlignment.Center),
            )
            Text(
                text = "Country:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(InfoAlignment.Center),
            )
            Text(
                text = "Canada",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(InfoAlignment.Center),
            )
            Text(
                text = "Country code:",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(InfoAlignment.Center),
            )
            Text(
                text = "CA",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(InfoAlignment.Center),
            )
        }
    }
}




