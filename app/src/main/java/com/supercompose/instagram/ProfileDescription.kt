package com.supercompose.instagram

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int,
    modifier: Modifier = Modifier
) {
    val letterSpacer = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacer,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            letterSpacing = letterSpacer,
            lineHeight = lineHeight
        )
        Text(
            text = url,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacer,
            lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.lastIndex) {
                            append(", ")
                        }
                    }
                    if (otherCount > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacer,
                lineHeight = lineHeight
            )
        }
    }
}
