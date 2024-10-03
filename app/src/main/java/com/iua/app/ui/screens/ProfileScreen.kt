package com.iua.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.ui.tooling.preview.Preview

//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.semantics.semantics
//import androidx.compose.ui.semantics.testTagsAsResourceId
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.iua.app.mock.Profile
//import com.iua.app.ui.components.profile.FooterContent
//import com.iua.app.ui.components.profile.MainProfileContent
//import com.iua.app.ui.components.profile.TopProfileLayout
//import com.iua.app.ui.view_models.ProfileViewModel

//@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
//@Composable
//fun ProfileScreen(
//    viewModel: ProfileViewModel = hiltViewModel()
//) {
//    val user: Profile by viewModel.profile.observeAsState(initial = Profile(0, "", "", "", "", ""))
//    val popularProjects by viewModel.popularProjects.collectAsState()
//
//    Scaffold(modifier = Modifier.semantics {
//        testTagsAsResourceId = true
//    },
//        containerColor = Color.Transparent,
//        contentColor = MaterialTheme.colorScheme.onBackground,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Profile") },
//                navigationIcon = {
//                    IconButton(onClick = { /*onGoBack*/ }) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { }) {
//                        Icon(Icons.Default.MoreVert, contentDescription = "More")
//                    }
//                },
//                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                    containerColor = Color.Transparent,
//                ),
//            )
//        }) { padding ->
//        ProfileContent(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(padding)
//        ) {
//            TopProfileLayout(user)
//            //MainProfileContent(popularProjects)
//            FooterContent()
//        }
//    }
//}
//
//@Composable
//fun ProfileContent(
//    modifier: Modifier = Modifier, content: @Composable () -> Unit
//) {
//    Column(modifier) {
//        content()
//    }
//}


import androidx.compose.ui.draw.clip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iua.app.mock.Profile
import com.iua.app.ui.view_models.ProfileViewModel


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val user: Profile by viewModel.profile.observeAsState(initial = Profile(0, "", "", "", "", ""))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            TopProfileLayout(user)
            Spacer(modifier = Modifier.height(16.dp))
            UnifiedSection()
        }
        CustomButtons(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun TopProfileLayout(user: Profile) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFF1C1C1C)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccountCircle, // Puedes usar Coil para una imagen real.
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    tint = Color.Green
                )
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(text = user.name, style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
                    Text(text = "https://ar.lemon.me/${user.name}", style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun UnifiedSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF1C1C1E))
            .padding(16.dp)
    ) {
        Text(
            text = "Mi cuenta y Configuración", // Combine the sections' name
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp), // Rounded edges for the card
            colors = CardDefaults.cardColors( // Set the background color for the card
                containerColor = Color(0xFF333333)
            ),
            elevation = CardDefaults.cardElevation(4.dp) // Set elevation
        ) {
            Column {
                // First item: "Datos personales"
                ProfileItem(
                    icon = Icons.Default.Person,
                    title = "Datos personales",
                    description = "Nombre, mail y DNI."
                )
                Divider(color = Color(0xFF444444), thickness = 1.dp) // Divider between items

                // Second item: "Personalización"
                ProfileItem(
                    icon = Icons.Default.Settings,
                    title = "Personalización",
                    description = "Notificaciones, modo oscuro, etc."
                )
            }
        }
    }
}

@Composable
fun ProfileItem(icon: ImageVector, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = description, color = Color.Gray, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun CustomButtons(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF1C1C1E))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "Necesito ayuda" Button
        Button(
            onClick = { /* Handle "Necesito ayuda" click */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF333333), // Dark background
                contentColor = Color.White // White text/icon
            ),
            shape = RoundedCornerShape(50) // Rounded corners
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Headset, // Headset icon
                    contentDescription = "Help icon",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Necesito ayuda", color = Color.White)
            }
        }

        // "Cerrar sesión" Button
        TextButton(
            onClick = { /* Handle "Cerrar sesión" click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Cerrar sesión",
                color = Color(0xFF00FF00), // Green text
                fontWeight = FontWeight.Bold
            )
        }
    }
}


//@Composable
//fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
//    val user: Profile by viewModel.profile.observeAsState(initial = Profile(0, "", "", "", "", ""))
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.Black)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            TopProfileLayout(user)
//            Spacer(modifier = Modifier.height(16.dp))
//            UnifiedSection()
//        }
//        CustomButtons()
//    }
//}
//
//@Composable
//fun TopProfileLayout(user: Profile) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .padding(2.dp),
//        shape = RoundedCornerShape(16.dp),
//        color = Color(0xFF1C1C1C)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(
//                    imageVector = Icons.Default.AccountCircle, // Puedes usar Coil para una imagen real.
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(50.dp)
//                        .clip(CircleShape),
//                    tint = Color.Green
//                )
//                Column(modifier = Modifier.padding(start = 12.dp)) {
//                    Text(text = user.name, style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
//                    Text(text = "https://ar.lemon.me/${user.name}", style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
//                }
//            }
//            Icon(
//                imageVector = Icons.Default.ArrowForward,
//                contentDescription = null,
//                tint = Color.Green,
//                modifier = Modifier.size(24.dp)
//            )
//        }
//    }
//}
//
//@Composable
//fun UnifiedSection() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = Color(0xFF1C1C1E))
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Mi cuenta y Configuración", // Combine the sections' name
//            color = Color.White,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            shape = RoundedCornerShape(16.dp), // Rounded edges for the card
//            colors = CardDefaults.cardColors( // Set the background color for the card
//                containerColor = Color(0xFF333333)
//            ),
//            elevation = CardDefaults.cardElevation(4.dp) // Set elevation
//        ) {
//            Column {
//                // First item: "Datos personales"
//                ProfileItem(
//                    icon = Icons.Default.Person,
//                    title = "Datos personales",
//                    description = "Nombre, mail y DNI."
//                )
//                Divider(color = Color(0xFF444444), thickness = 1.dp) // Divider between items
//
//                // Second item: "Personalización"
//                ProfileItem(
//                    icon = Icons.Default.Settings,
//                    title = "Personalización",
//                    description = "Notificaciones, modo oscuro, etc."
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ProfileItem(icon: ImageVector, title: String, description: String) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = null,
//            tint = Color.White,
//            modifier = Modifier.size(24.dp)
//        )
//        Spacer(modifier = Modifier.width(16.dp))
//        Column {
//            Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
//            Text(text = description, color = Color.Gray, fontSize = 12.sp)
//        }
//        Spacer(modifier = Modifier.weight(1f))
//        Icon(
//            imageVector = Icons.Default.ArrowForwardIos,
//            contentDescription = null,
//            tint = Color.Gray,
//            modifier = Modifier.size(16.dp)
//        )
//    }
//}
//
//@Composable
//fun CustomButtons() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF1C1C1E))
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter)
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // "Necesito ayuda" Button
//            Button(
//                onClick = { /* Handle "Necesito ayuda" click */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF333333), // Dark background
//                    contentColor = Color.White // White text/icon
//                ),
//                shape = RoundedCornerShape(50) // Rounded corners
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Headset, // Headset icon
//                        contentDescription = "Help icon",
//                        tint = Color.White
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(text = "Necesito ayuda", color = Color.White)
//                }
//            }
//
//            // "Cerrar sesión" Button
//            TextButton(
//                onClick = { /* Handle "Cerrar sesión" click */ },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Cerrar sesión",
//                    color = Color(0xFF00FF00), // Green text
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }
//    }
//}

//@Composable
//fun ProfileItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, description: String) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        shape = RoundedCornerShape(8.dp),
//        color = Color(0xFF1C1C1E)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(
//                    imageVector = icon,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(24.dp)
//                        .padding(end = 8.dp),
//                    tint = Color.Gray
//                )
//                Column {
//                    Text(text = title, style = MaterialTheme.typography.bodyLarge.copy(color = Color.White))
//                    if (description.isNotEmpty()) {
//                        Text(
//                            text = description,
//                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
//                            maxLines = 1
//                        )
//                    }
//                }
//            }
//            Icon(
//                imageVector = Icons.Default.ArrowForward,
//                contentDescription = null,
//                tint = Color.Gray,
//                modifier = Modifier.size(24.dp)
//            )
//        }
//    }
//}



@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen()
}
