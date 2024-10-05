package com.iua.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.components.profile.TopAppBarComponent


@Composable
fun NotificationsScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        topBar = {
            TopAppBarComponent(navController, "Notificaciones", Icons.AutoMirrored.Filled.ArrowBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Section: Notificaciones push
            Text(
                text = "Notificaciones push",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            NotificationToggleItem("Recordatorios", "Recordatorios de eventos")
            NotificationToggleItem("Eventos Cerca", "Eventos cerca de ti")
            NotificationToggleItem("Eventos", "Eventos que quizas te gusten")
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "E-mails",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            NotificationToggleItem("Ingreso", "Ingreso a tu cuenta ")
            NotificationToggleItem("Promocionales", "Eventos con descuentos")
        }
    }
}

@Composable
fun NotificationToggleItem(title: String, subtitle: String) {
    var isChecked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = subtitle,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(checkedThumbColor = Color.Green)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationsScreen(
        navController = rememberNavController()
    )
}