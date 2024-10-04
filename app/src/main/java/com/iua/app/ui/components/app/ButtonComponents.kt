package com.iua.app.ui.components.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

//
//LoginButton(loginEnable) {
//coroutineScope.launch { viewModel.onLoginSelected() }
//}


@Composable
fun ButtonComponent(text: String, enabled: Boolean = false, onButtonSelected: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp) // Ajusta el valor de padding según sea necesario
    ) {
        Button(
            onClick = { onButtonSelected() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.secondary,
            ),
            enabled = enabled
        ) {
            Text(text = text, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onPrimary)
        }
    }


//    Button(
//        onClick = { onButtonSelected() },
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(48.dp),
//        contentPadding = PaddingValues(),
//        colors = ButtonDefaults.buttonColors(
//            contentColor = Color.Transparent,
//        ),
//        enabled = enabled
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(48.dp)
//                .background(
//                    brush = Brush.linearGradient(listOf(Color(0xFF00BFA5), Color(0xFF00BFA5))),
//                    shape = RoundedCornerShape(50.dp)
//                ), contentAlignment = Alignment.Center,
//        ) {
//            Text(
//                text = text,
//                color = Color.White
//            )
//        }
//    }
}