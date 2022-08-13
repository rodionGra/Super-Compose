package com.tutorial.supercompose.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
fun Modifiers() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxHeight(0.5F)
            .padding(30.dp)
            .border(5.dp, Color.Blue)
            .padding(5.dp)
            .border(5.dp, Color.Cyan)
            .padding(5.dp)
            .width(1200.dp) // якщо екран більше, тоді підлаштується під ширину
//            .requiredWidth(600.dp) //не враховує ширину екрану
    ) {
        Text("Hello", Modifier.clickable {
            //handle click
        })
        Spacer(modifier = Modifier.height(50.dp)) //вставляє пустоту
        Text(
            "Android!!",
            Modifier.offset(40.dp, 20.dp) //здвигає праворуч та вниз
        )
    }
}
