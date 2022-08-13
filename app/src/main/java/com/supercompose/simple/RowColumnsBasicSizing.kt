package com.tutorial.supercompose.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun RowColumnsBasicSizing() {
    Column {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(300.0F.dp)
                .height(300.0F.dp)
                .background(color = Color.Cyan),
        ) {
            Text(text = "Hello")
            Text(text = "Word")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .width(300.0F.dp)
                .height(300.0F.dp)
                .background(color = Color.Magenta),
        ) {
            Text(text = "Hello")
            Text(text = "Word")
            Text(text = "!!!")
        }
    }
}