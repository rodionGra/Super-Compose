package com.tutorial.supercompose.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supercompose.R

@Preview(showBackground = true)
@Composable
fun StylingTextScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF101010))
    ) {
        Text(
            modifier = Modifier
                .background(color = Color.Cyan)
                .padding(10.dp)
                .background(color = Color.Blue),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append("J")
                }
                append("etpack")
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append("C")
                }
                append("ompose")
            },
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.qahiri, FontWeight.Thin)),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}
