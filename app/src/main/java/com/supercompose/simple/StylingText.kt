package com.supercompose.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.supercompose.R
import java.text.BreakIterator

@Composable
fun StylingTextScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(red = 232, green = 232, blue = 232))
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
        Spacer(modifier = Modifier.height(16.dp))
        ExpandableText(
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
            expandText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            collapseText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
            maxLinesCollapsed = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StylingTextScreenPreview() {
    StylingTextScreen()
}

const val COLLAPSED_SPAN = "collapsed_span"
const val EXPANDED_SPAN = "expanded_span"
const val LINE_EXTRA_SPACE = 5

@Composable
fun ExpandableText(
    text: String,
    expandText: String,
    modifier: Modifier = Modifier,
    expandColor: Color = Color.Unspecified,
    collapseText: String? = null,
    collapseColor: Color = Color.Unspecified,
    maxLinesCollapsed: Int = 5,
    style: TextStyle = TextStyle.Default,
) {
    BoxWithConstraints(modifier) {
        val paragraph = Paragraph(
            text = text,
            style = style,
            constraints = Constraints(maxWidth = constraints.maxWidth),
            density = LocalDensity.current,
            fontFamilyResolver = LocalFontFamilyResolver.current,
        )

        val trimLineRange: IntRange? = if (paragraph.lineCount > maxLinesCollapsed) {
            paragraph.getLineStart(maxLinesCollapsed - 1)..paragraph.getLineEnd(maxLinesCollapsed - 1)
        } else {
            null
        }
        val expandState = SpanState(expandText, expandColor)
        val collapseState = collapseText?.let { SpanState(it, collapseColor) }
        val state = rememberState(text, expandState, collapseState, trimLineRange, style)

        ClickableText(text = state.annotatedString, style = style, onClick = { position ->
            val annotation = state.getClickableAnnotation(position)
            when (annotation?.tag) {
                COLLAPSED_SPAN -> state.expandState = State.ExpandState.Expanded
                EXPANDED_SPAN -> state.expandState = State.ExpandState.Collapsed
                else -> Unit
            }
        })
    }
}

@Composable
private fun rememberState(
    text: String,
    expandSpanState: SpanState,
    collapseSpanState: SpanState?,
    lastLineRange: IntRange?,
    style: TextStyle,
): State {
    return remember(text, expandSpanState, collapseSpanState, lastLineRange, style) {
        State(
            text = text,
            expandSpanState = expandSpanState,
            collapseSpanState = collapseSpanState,
            lastLineTrimRange = lastLineRange,
            style = style,
        )
    }
}

private data class SpanState(
    val text: String,
    val color: Color,
)

private class State(
    text: String,
    expandSpanState: SpanState,
    collapseSpanState: SpanState?,
    lastLineTrimRange: IntRange?,
    style: TextStyle,
) {
    enum class ExpandState {
        Collapsed, Expanded,
    }

    private val defaultAnnotatedText = buildAnnotatedString { append(text) }
    private val collapsedAnnotatedText: AnnotatedString
    private val expandedAnnotatedText: AnnotatedString

    init {
        collapsedAnnotatedText = lastLineTrimRange?.let {
            val lastLineLen = lastLineTrimRange.last - lastLineTrimRange.first + 1
            val expandTextLen = getSafeLength(expandSpanState.text)
            val collapsedText =
                text.take(lastLineTrimRange.last + 1)
                    .dropLast(minOf(lastLineLen, expandTextLen + LINE_EXTRA_SPACE))
            val collapsedTextLen = getSafeLength(collapsedText)
            val expandSpanStyle =
                style.merge(TextStyle(color = expandSpanState.color)).toSpanStyle()
            buildAnnotatedString {
                append(collapsedText)
                append(expandSpanState.text)
                addStyle(
                    expandSpanStyle,
                    start = collapsedTextLen,
                    end = collapsedTextLen + expandTextLen
                )
                addStringAnnotation(
                    tag = COLLAPSED_SPAN,
                    annotation = "",
                    start = collapsedTextLen,
                    end = collapsedTextLen + expandTextLen
                )
            }
        } ?: defaultAnnotatedText

        expandedAnnotatedText = collapseSpanState?.let { span ->
            val collapseStyle = style.merge(TextStyle(color = span.color)).toSpanStyle()
            val textLen = getSafeLength(text)
            val collapsePostfix = "\n${span.text}"
            val collapseLen = getSafeLength(collapsePostfix)
            buildAnnotatedString {
                append(text)
                append(collapsePostfix)
                addStyle(collapseStyle, start = textLen, end = textLen + collapseLen)
                addStringAnnotation(
                    tag = EXPANDED_SPAN,
                    annotation = "",
                    start = textLen,
                    end = textLen + collapseLen
                )
            }
        } ?: defaultAnnotatedText
    }

    var annotatedString: AnnotatedString by mutableStateOf(collapsedAnnotatedText)
        private set
    private val _expandState = mutableStateOf(ExpandState.Collapsed)
    var expandState: ExpandState
        set(value) {
            _expandState.value = value
            annotatedString = when (value) {
                ExpandState.Collapsed -> collapsedAnnotatedText
                ExpandState.Expanded -> expandedAnnotatedText
            }
        }
        get() = _expandState.value

    fun getClickableAnnotation(position: Int): AnnotatedString.Range<String>? {
        return annotatedString.getStringAnnotations(position, position).firstOrNull {
            it.tag == COLLAPSED_SPAN || it.tag == EXPANDED_SPAN
        }
    }
}

private fun getSafeLength(text: String): Int {
    val iterator = BreakIterator.getCharacterInstance()
    iterator.setText(text)
    return iterator.last()
}
