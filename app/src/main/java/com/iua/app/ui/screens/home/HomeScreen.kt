package com.iua.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.iua.app.R
import com.iua.app.mock.Event
import com.iua.app.ui.components.BottomBar
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
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                BottomBarNavigation(homeNavController = homeNavController, mainNavController = navController)
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val events by viewModel.events.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    LazyColumn {
        items(events) { event ->
            val isFavorite = favorites[event.id] ?: false
            EventCard(event = event,
                navigateToDescription = {
                    navController.navigate("${AppScreens.EventDetailScreen.routes}/${event.id}")
                },
                isFavorite = isFavorite,
                onToggleFavorite = { viewModel.toggleFavorite(event.id) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* TODO implementar la acción de búsqueda */ },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.home_search),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                        cursorColor = MaterialTheme.colorScheme.onBackground,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(56.dp)
                        .align(Alignment.CenterVertically)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                )
            }
        },
        navigationIcon = {
            Row(
                modifier = Modifier.fillMaxHeight().padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo_prev),
                        contentDescription = "Logo",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight().padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate(AppScreens.ProfileScreen.routes) }) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "Ir al perfil",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(0.dp))
            .height(80.dp)  // Altura de la TopAppBar
            .fillMaxWidth()
    )
}

@Composable
fun EventCard(
    event: Event,
    navigateToDescription: (String) -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(shape = RoundedCornerShape(16.dp), width = 1.dp, color = Color.Gray)
            .clickable { navigateToDescription(event.id) }
    ) {
        PostImage(event, Modifier.padding(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
        ) {
            EventTitle(event)
            EventDescription(event)
        }
        Column(
            modifier = Modifier
                .padding(vertical = 1.dp, horizontal = 6.dp)
                .fillMaxHeight()
                .height(120.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            FavoriteButton(
                isFavorite = isFavorite,
                onClick = onToggleFavorite,
                modifier = Modifier.clearAndSetSemantics {}
            )
            IconButton(
                onClick = { navigateToDescription(event.id) },
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Go to event details",
                    modifier = Modifier.padding(4.dp),
                )
            }
        }
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
            .size(100.dp, 100.dp)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun EventTitle(post: Event) {
    Text(
        text = post.title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = MaterialTheme.typography.displayLarge.fontWeight),
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
