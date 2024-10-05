package com.iua.app.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.iua.app.R
import com.iua.app.mock.Profile
import com.iua.app.ui.components.TopAppBarComponent
import com.iua.app.ui.view_models.ProfileViewModel

@Composable
fun PersonalDataScreen(viewModel: ProfileViewModel = hiltViewModel(), navController: NavHostController) {
    val user: Profile by viewModel.profile.observeAsState(initial = Profile(0, "", "", "", "", "", ""))
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBarComponent(
                navController = navController,
                 stringResource(id = R.string.personal_data_top_bar),
                 Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(vertical = 5.dp ,horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = user.avatar), // URL or resource ID
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            ProfileDataSection(
                label = stringResource(id = R.string.personal_data_name),
                value = user.name,
                onEditClick = { /* TODO Acción de edición */ }
            )
            ProfileDataSection(
                label = stringResource(id = R.string.personal_data_last_name),
                value = user.lastName,
                onEditClick = { /* TODO Acción de edición */ }
            )

            ProfileDataSection(
                label = stringResource(id = R.string.personal_data_email),
                value = user.email,
                onEditClick = { /* TODO Acción de edición */ }
            )

            ProfileDataSection(
                label = stringResource(id = R.string.personal_data_phone),
                value = user.phone,
                onEditClick = { /* TODO Acción de edición */ }
            )

            ProfileDataSection(
                label = stringResource(id = R.string.personal_data_id),
                value = user.dni,
                onEditClick = { /* TODO Acción de edición */ }
            )

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = { /* TODO Acción para eliminar cuenta */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Red
                )
            ) {
                Text(
                    text = stringResource(id = R.string.personal_data_delete_account_button),
                    color = Color.Red,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun ProfileDataSection(label: String, value: String, onEditClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = value,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = MaterialTheme.typography.displayLarge.fontWeight),
                )
            }
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDataScreenPreview() {
    PersonalDataScreen(
        navController = rememberNavController()
    )
}