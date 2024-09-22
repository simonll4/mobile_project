package com.iua.app.ui.components.login

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot Password?",
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier.clickable { },
        color = MaterialTheme.colorScheme.primary,
        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
    )
}