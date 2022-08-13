package com.tutorial.supercompose.difftest

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@Composable
fun CoilTestScreen() {
    val link = "https://imgr.co/images/12-greenery_greeny_bug.jpg"

    SideEffect {
        val t = flow {
            var counter = 0
            while (true) {
                Log.d("DEBUG_TAG", "emit mst = $counter")
                emit(counter)
                counter++
                delay(2_000L)
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            t.collect {
                Log.d("DEBUG_TAG", "start collect msg = $it")
                if (it > 2) {
                    delay(5000L)
                }
                Log.d("DEBUG_TAG", "end collect msg $it")
            }
        }

        val flow = MutableSharedFlow<String>()
        val emitResult = flow.tryEmit("newValue")
        Log.d("FLOW_TEST", "emit result = $emitResult")
        GlobalScope.launch {
            flow.collect {
                Log.d("FLOW_TEST", "flow value  = $it")
            }
        }
        Thread.sleep(5_000L)
        flow.tryEmit("newValue2")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "TestTExt",
            modifier = Modifier.align(Alignment.Center)
        )

        val painter =
            rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = link)
                    .scale(Scale.FILL)
                    .size(Size.ORIGINAL)
                    .apply(block = fun ImageRequest.Builder.() {
                        transformations(
                            RoundedCornersTransformation(25F)
                        )
                    }).build()
            )
        Image(
            painter = painter,
            contentDescription = null,
        )

        /*AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(link)
                .build(),
            contentDescription = null,
            imageLoader = LocalContext.current.imageLoader,
            transform = {
                RoundedCornersTransformation(10F)
                it
            }
            //transform = RoundedCornersTransformation(10F)
        )*/
    }
}
