package com.iua.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.iua.app.mock.Event
import com.iua.app.ui.components.home.BottomBar
import com.iua.app.ui.navigation.BottomBarNavigation
import com.iua.app.ui.view_models.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val homeNavController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(homeNavController = homeNavController) }) {
        BottomBarNavigation(homeNavController = homeNavController)
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
            EventCard(
                event = event,
                navigateToDescription = { /* TODO: Implement navigation */ },
                isFavorite = isFavorite,
                onToggleFavorite = { viewModel.toggleFavorite(event.id) }
            )
        }
    }
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
        FavoriteButton(
            isFavorite = isFavorite,
            onClick = onToggleFavorite,
            modifier = Modifier
                .clearAndSetSemantics {}
                .padding(vertical = 2.dp, horizontal = 6.dp)
        )
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
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = { onClick() },
        modifier = modifier.semantics {
            this.onClick(action = null)
        }
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null
        )
    }
}