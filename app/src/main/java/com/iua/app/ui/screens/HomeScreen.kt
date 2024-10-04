package com.iua.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.iua.app.mock.Event
import com.iua.app.ui.components.home.BottomBar
import com.iua.app.ui.navigation.AppScreens
import com.iua.app.ui.navigation.BottomBarNavigation
import com.iua.app.ui.view_models.HomeViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    val homeNavController = rememberNavController()
    Scaffold(topBar = {
        HomeTopBar(navController)
    }, bottomBar = { BottomBar(homeNavController = homeNavController) }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
            ) {
                BottomBarNavigation(homeNavController = homeNavController)
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val events by viewModel.events.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    LazyColumn {
        items(events) { event ->
            val isFavorite = favorites[event.id] ?: false
            EventCard(event = event,
                navigateToDescription = { /* TODO: Implement navigation */ },
                isFavorite = isFavorite,
                onToggleFavorite = { viewModel.toggleFavorite(event.id) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavHostController) {
    TopAppBar(title = {
        OutlinedTextField(value = "",
            onValueChange = { /* Puedes implementar la acción de búsqueda */ },
            placeholder = { Text(text = "Buscar en la app", color = Color.White) },
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color.White
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
    }, navigationIcon = {
        IconButton(onClick = {}) {
            Icon(
                Icons.Default.Apps, contentDescription = "Logo", tint = Color.White
            )
        }
    }, actions = {
        IconButton(onClick = { navController.navigate(AppScreens.ProfileScreen.routes) }) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = "Ir al perfil",
                tint = Color.White
            )
        }
    }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent
    ), modifier = Modifier.background(Color(0xFF203AA2))
    )
}

@Composable
fun EventCard(
    event: Event,
    navigateToDescription: (String) -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    Row(modifier = Modifier.clickable { navigateToDescription(event.id) }) {
        PostImage(event, Modifier.padding(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
        ) {
            EventTitle(event)
            EventDescription(event)
        }
        FavoriteButton(isFavorite = isFavorite,
            onClick = onToggleFavorite,
            modifier = Modifier
                .clearAndSetSemantics {}
                .padding(vertical = 2.dp, horizontal = 6.dp))
    }
}

@Composable
fun EventDescription(event: Event) {
    Text(
        text = event.description,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun PostImage(event: Event, modifier: Modifier = Modifier) {
    AsyncImage(
        model = event.image,
        contentDescription = null,
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun EventTitle(post: Event) {
    Text(
        text = post.title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    IconToggleButton(checked = isFavorite,
        onCheckedChange = { onClick() },
        modifier = modifier.semantics {
            this.onClick(action = null)
        }) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null
        )
    }
}