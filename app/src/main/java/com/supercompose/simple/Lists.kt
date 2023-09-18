package com.supercompose.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

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

@Composable
@Preview
fun LazyRowExample() {
    LazyColumn {
        item {
            LazyRow(
                //modifier = Modifier.height(600.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                this.items(5) {
                    if (it == 4) {
                        Box(
                            Modifier
                                .height(IntrinsicSize.Max)
                                .width(200.dp)
                                .background(Color.Magenta)
                        ) {
                            Text(modifier = Modifier.align(Alignment.Center), text = it.toString())
                        }
                    } else {
                        Box(
                            Modifier
                                .height(300.dp)
                                .width(200.dp)
                                .background(Color.Blue)
                        ) {
                            Text(modifier = Modifier.align(Alignment.Center), text = it.toString())
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            LazyRow(
                //modifier = Modifier.height(600.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                this.items(5) {
                    if (it == 4) {
                        Box(
                            Modifier
                                .fillMaxHeight()
                                .width(200.dp)
                                .background(Color.Yellow)
                        ) {
                            Text(modifier = Modifier.align(Alignment.Center), text = it.toString())
                        }
                    } else {
                        Box(
                            Modifier
                                .height(300.dp)
                                .width(200.dp)
                                .background(Color.Green)
                        ) {
                            Text(modifier = Modifier.align(Alignment.Center), text = it.toString())
                        }
                    }
                }
            }
        }
    }
}
