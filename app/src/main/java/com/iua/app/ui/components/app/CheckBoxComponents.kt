package com.iua.app.ui.components.app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckBoxComponent(
    text: String,
    clickableWords: List<String>,
    checkState: Boolean,
    onCheckChange: (Boolean) -> Unit,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checkState, onCheckedChange = onCheckChange)

        ClickableTextComponent(
            text = text,
            clickableWords = clickableWords,
            onClick = onClick
        )
    }
}