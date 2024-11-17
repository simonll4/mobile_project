package com.iua.app.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.iua.app.domain.model.Resource
import com.iua.app.ui.components.ListComponent
import com.iua.app.ui.navigation.AppScreens
import com.iua.app.ui.view_models.FavoriteViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val favoriteEventsResource by viewModel.favoriteEvents.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshFavoriteEvents()
    }

    when (val resource = favoriteEventsResource) {
        is Resource.Loading -> {
            // Handle loading state if needed
        }

        is Resource.Success -> {
            if (resource.data?.isEmpty() == true) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "No hay eventos marcados como favoritos",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                ListComponent(
                    eventsResource = favoriteEventsResource,
                    isRefreshing = false,
                    onRefresh = { viewModel.fetchFavoriteEvents() },
                    onEventClick = { event ->
                        navController.navigate("${AppScreens.EventDetailScreen.routes}/${event.id}")
                    },
                    onToggleFavorite = { event ->
                        viewModel.toggleFavorite(event)
                    }
                )
            }
        }

        is Resource.Error -> {
            // Handle error state if needed
        }
    }
}