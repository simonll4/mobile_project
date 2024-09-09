package com.iua.app.ui.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.iua.app.domain.model.EventsModel


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Box(
        androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                viewModel.saveEvent(EventsModel("1", "Evento 1"))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.secondary,
            ),
        ) {
            Text(text = "Home")
        }
    }
}

