package com.iua.app.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.iua.app.mock.Profile
import com.iua.app.ui.components.app.BadgeComponent

@Composable
fun TopProfileLayout(user: Profile) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = user.avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(70.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Matias", style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "@Matwey",
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Hola que tal soy Matias, me gusta hacer aplicaciones front y elegir la paleta de colores, ademas de hacer un buen diseño",
                style = MaterialTheme.typography.bodySmall,
            )
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BadgeComponent(icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }, text = {
                        Text(
                            text = "Cordoba, Argentina",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    })
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between items
                    BadgeComponent(icon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }, text = {
                        Text(
                            text = "Matifront@gmail.com",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    })
                }
            }
        }
    }
}

//
//package com.iua.app.ui.components.profile
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowForward
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import coil.compose.rememberAsyncImagePainter
//import com.iua.app.mock.Profile
//
//@Composable
//fun TopProfileLayout(user: Profile) {
//    // Fondo oscuro y bordes redondeados
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        shape = RoundedCornerShape(16.dp), // Borde redondeado
//        color = Color(0xFF1C1C1E) // Fondo oscuro
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp), // Padding interno
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween // Espacio entre el contenido y la flecha
//        ) {
//            // Avatar del usuario
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = rememberAsyncImagePainter(model = user.avatar),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .clip(CircleShape) // Forma circular
//                        .size(50.dp) // Tamaño del avatar
//                )
//
//                Column(
//                    modifier = Modifier.padding(start = 12.dp) // Espacio entre avatar y texto
//                ) {
//                    // Nombre de usuario
//                    Text(
//                        text = user.name,
//                        style = MaterialTheme.typography.titleLarge.copy(color = Color.White) // Texto en blanco
//                    )
//
//                    // URL del perfil
//                    Text(
//                        text = "https://ar.lemon.me/${user.name}",
//                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
//                        overflow = TextOverflow.Ellipsis, // Comprime si es demasiado largo
//                        maxLines = 1 // Limita a una línea
//                    )
//                }
//            }
//
//            // Ícono de flecha
//            Icon(
//                imageVector = Icons.Default.ArrowForward,
//                contentDescription = null,
//                tint = Color.Green, // Flecha verde
//                modifier = Modifier.size(24.dp) // Tamaño del ícono
//            )
//        }
//    }
//}
