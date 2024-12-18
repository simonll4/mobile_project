package com.iua.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iua.app.R
import com.iua.app.ui.components.TopAppBarComponent


@Composable
fun NotificationsScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBarComponent(
                navController,
                stringResource(R.string.notifications_top_bar),
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.notifications_push_title),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            NotificationToggleItem(
                stringResource(R.string.notifications_reminders_title),
                stringResource(R.string.notifications_reminders_subtitle)
            )
//            NotificationToggleItem(
//                stringResource(R.string.notifications_events_nearby_title),
//                stringResource(R.string.notifications_events_nearby_subtitle)
//            )
//            NotificationToggleItem(
//                stringResource(R.string.notifications_possible_events_title),
//                stringResource(R.string.notifications_possible_events_subtitle)
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//            Text(
//                text = stringResource(R.string.notifications_email_title),
//                color = MaterialTheme.colorScheme.onBackground,
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier.padding(vertical = 16.dp)
//            )
//            NotificationToggleItem(
//                stringResource(R.string.notifications_login_title),
//                stringResource(R.string.notifications_login_subtitle)
//            )
//            NotificationToggleItem(stringResource(R.string.notifications_promotional_title), stringResource(R.string.notifications_promotional_subtitle))
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
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary
            )
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