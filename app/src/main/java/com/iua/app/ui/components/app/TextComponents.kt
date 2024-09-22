package com.iua.app.ui.components.app

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalTextComponent(text: String) {
    Text(
        text = text, modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 35.dp), style = TextStyle(
            fontSize = 24.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal
        ), color = Color(0xFF000000), // cambiar esto por un color de la paleta de colores
        textAlign = TextAlign.Center
    )
}

@Composable
fun TitleTextComponent(text: String) {
    Text(
        text = text, modifier = Modifier
            .fillMaxWidth()
            .heightIn(), style = TextStyle(
            fontSize = 30.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal
        ), color = Color(0xFF000000), textAlign = TextAlign.Center
    )
}

@Composable
fun ClickableTextComponent(
    text: String,
    clickableWords: List<String>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val annotatedText = buildAnnotatedString {
        val words = text.split(" ")
        for (word in words) {
            val trimmedWord = word.trim()
            val matchingClickableWord =
                clickableWords.find { it.equals(trimmedWord, ignoreCase = true) }
            if (matchingClickableWord != null) {
                pushStringAnnotation(tag = "CLICKABLE", annotation = matchingClickableWord)
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("$trimmedWord ")
                }
                pop()
            } else {
                append("$word ")
            }
        }
    }
    ClickableText(
        text = annotatedText,
        modifier = modifier, // Aquí se aplica el modificador
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "CLICKABLE", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    onClick(annotation.item)
                }
        }
    )
}


