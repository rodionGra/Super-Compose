package com.tutorial.supercompose.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun NotLazyColumn() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..50) {
            val randomColor =
                Color(Random.nextInt(from = 0, until = 256), Random.nextInt(), Random.nextInt())
            Text(
                text = "Item = $i",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(randomColor)
                    .padding(24.dp)
            )
        }
    }
}

@Composable
fun LazyColumnExample() {
    LazyColumn {
        itemsIndexed(
            items = listOf("This", "is", "jetpack", "compose", "!!!")
        ) { index, item ->
            Text(
                text = "$item index = $index",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color(
                            Random.nextInt(from = 0, until = 256),
                            Random.nextInt(),
                            Random.nextInt()
                        )
                    )
                    .padding(24.dp)
            )
        }
        items(5000) {
            Text(
                text = "Item = $it",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color(
                            Random.nextInt(from = 0, until = 256),
                            Random.nextInt(),
                            Random.nextInt()
                        )
                    )
                    .padding(24.dp)
            )
        }
    }
}
