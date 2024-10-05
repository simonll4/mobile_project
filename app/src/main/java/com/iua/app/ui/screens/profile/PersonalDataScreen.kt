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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.iua.app.mock.Profile
import com.iua.app.ui.components.profile.TopAppBarComponent
import com.iua.app.ui.view_models.ProfileViewModel

@Composable
fun PersonalDataScreen(viewModel: ProfileViewModel = hiltViewModel(), navController: NavHostController) {
    val user: Profile by viewModel.profile.observeAsState(initial = Profile(0, "", "", "", "", "", ""))
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        topBar = {
            TopAppBarComponent(
                navController = navController,
                 "Mis datos personales",
                 Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
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
                label = "Nombre",
                value = user.name,
                onEditClick = { /* Acción de edición */ }
            )
            ProfileDataSection(
                label = "Apellido",
                value = user.lastName,
                onEditClick = { /* Acción de edición */ }
            )

            ProfileDataSection(
                label = "Correo electrónico",
                value = user.email,
                onEditClick = { /* Acción de edición */ }
            )

            ProfileDataSection(
                label = "Telefono",
                value = user.phone,
                onEditClick = { /* Acción de edición */ }
            )

            ProfileDataSection(
                label = "Número de documento",
                value = user.dni,
                onEditClick = { /* Acción de edición */ }
            )

            Spacer(modifier = Modifier.weight(1f)) // Empuja el botón de eliminar hacia abajo

            // Botón de eliminar cuenta
            TextButton(
                onClick = { /* Acción para eliminar cuenta */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Eliminar cuenta",
                    color = Color.Red,
                    fontSize = 16.sp
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
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White
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