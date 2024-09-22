package com.iua.app.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iua.app.ui.components.app.TitleTextComponent

@Composable
fun TermsAndConditionsScren() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        TitleTextComponent(text = "Términos y condiciones")
    }
}