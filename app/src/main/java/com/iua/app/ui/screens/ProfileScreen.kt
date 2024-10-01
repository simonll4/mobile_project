package com.iua.app.ui.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Devices
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import coil.compose.rememberAsyncImagePainter
//import com.iua.app.mock.Profile
//import com.iua.app.ui.view_models.ProfileViewModel
//
//@Composable
//fun ProfileScreen() {
//    ProfileView()
//}
//
//
//@Composable
//fun ProfileView(viewModel: ProfileViewModel = hiltViewModel()) {
//    val user: Profile by viewModel.profile.observeAsState(initial = Profile(0, "", "", "", "", ""))
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(120.dp)
//                .background(MaterialTheme.colorScheme.primary)
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(120.dp)
//                    .align(Alignment.BottomCenter)
//                    .offset(y = 60.dp)
//                    .clip(CircleShape)
//                    .background(Color.White)
//                    .border(4.dp, Color.White, CircleShape)
//            ) {
//                Image(
//                    painter = rememberAsyncImagePainter(model = user.avatar),
//                    contentDescription = "Profile Picture",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize(),
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(70.dp))
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Text(
//                text = "${user.name} ${user.lastName}",
//                style = MaterialTheme.typography.headlineLarge,
//                fontWeight = FontWeight.Bold
//            )
//        }
//        Spacer(modifier = Modifier.height(20.dp))
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Button(
//                onClick = { /* Follow Action */ },
//                modifier = Modifier.weight(1f)
//            ) {
//                Text("Follow")
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            OutlinedButton(
//                onClick = { /* Message Action */ },
//                modifier = Modifier.weight(1f)
//            ) {
//                Text("Message")
//            }
//        }
//    }
//}
//
//@Composable
//@Preview(device = Devices.PHONE, showSystemUi = true)
//fun ProfileScreenPreview() {
//    ProfileScreen()
//}

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iua.app.mock.Profile
import com.iua.app.mock.ProfilePopularList
import com.iua.app.ui.view_models.ProfileViewModel


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user: Profile by viewModel.profile.observeAsState(initial = Profile(0, "", "", "", "", ""))
    val popularProjects by viewModel.popularProjects.collectAsState()

    Scaffold(modifier = Modifier.semantics {
        testTagsAsResourceId = true
    },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                navigationIcon = {
                    IconButton(onClick = { /*onGoBack*/ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
            )
        }) { padding ->
        ProfileContent(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            TopProfileLayout(user)
            MainProfileContent(popularProjects)
            FooterContent()
        }
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier) {
        content()
    }
}

@Composable
fun TopProfileLayout(user: Profile) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
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
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Hola que tal soy Matias, me gusta hacer aplicaciones front y elegir la paleta de colores, ademas de hacer un buen diseño",
                style = MaterialTheme.typography.bodySmall,
            )
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageTextContent(icon = {
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
                    ImageTextContent(icon = {
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

@Composable
fun MainProfileContent(popularProjects: List<ProfilePopularList>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Popular",
                style = MaterialTheme.typography.titleMedium,
            )
            PopularContentList(popularProjects)
            HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))
        }
    }
}

@Composable
fun FooterContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Más opciones",
                style = MaterialTheme.typography.titleMedium,
            )
            MoreOptionsComp("Edit Profile", Icons.Default.Edit)
            MoreOptionsComp("Privacy Policy", Icons.Default.Policy)
            MoreOptionsComp("About", Icons.Default.Info)
        }
    }
}

@Composable
fun ImageTextContent(
    icon: @Composable () -> Unit, text: @Composable () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(5.dp))
        text()
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun PopularContentList(popularProjects: List<ProfilePopularList>) {
    LazyRow {
        items(items = popularProjects, itemContent = {
            Surface(
                modifier = Modifier
                    .width(250.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(8),
                border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Row(
                        modifier = Modifier.padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Whatsapp,
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }

                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = it.description,
                        style = MaterialTheme.typography.bodySmall, maxLines = 2,
                    )

                    Row(
                        modifier = Modifier.padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ImageTextContent(modifier = Modifier.padding(vertical = 5.dp), icon = {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(15.dp)
                            )
                        }, text = {
                            Text(
                                text = it.star,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        })
                        Spacer(modifier = Modifier.width(5.dp))
                        ImageTextContent(modifier = Modifier.padding(vertical = 5.dp), icon = {
                            Icon(
                                imageVector = Icons.Default.Whatsapp,
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(10.dp)
                            )
                        }, text = {
                            Text(
                                text = it.language,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        })
                    }
                }
            }
        })
    }
}

@Composable
fun MoreOptionsComp(name: String, icon: ImageVector) {
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(6.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = name, style = MaterialTheme.typography.labelLarge
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen()
}