package com.iua.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.Resource
import com.iua.app.ui.screens.home.EventCard


@Composable
fun ListComponent(
    eventsResource: Resource<MutableList<EventModel>>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onEventClick: (EventModel) -> Unit,
    onToggleFavorite: (EventModel) -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = onRefresh
    ) {
        when (eventsResource) {
            is Resource.Success -> {
                val events = eventsResource.data ?: emptyList()
                LazyColumn {
                    items(events) { event ->
                        EventCard(
                            event = event,
                            navigateToDescription = { onEventClick(event) },
                            isFavorite = event.isFavorite,
                            onToggleFavorite = { onToggleFavorite(event) }
                        )
                    }
                }
            }

            is Resource.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: ${eventsResource.message}")
                }
            }

            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp),
                        color = Color.Blue
                    )
                }
            }
        }
    }
}


//@Composable
//fun ListComponent(
//    eventsResource: Resource<MutableList<EventModel>>,
//    isRefreshing: Boolean,
//    onRefresh: () -> Unit,
//    onEventClick: (EventModel) -> Unit,
//    onToggleFavorite: (EventModel) -> Unit
//) {
//    SwipeRefresh(
//        state = rememberSwipeRefreshState(isRefreshing),
//        onRefresh = onRefresh
//    ) {
//        Crossfade(targetState = eventsResource, label = "") { state ->
//            when (state) {
//                is Resource.Success -> {
//                    val events = state.data ?: emptyList()
//                    LazyColumn {
//                        items(events) { event ->
//                            EventCard(
//                                event = event,
//                                navigateToDescription = { onEventClick(event) },
//                                isFavorite = event.isFavorite,
//                                onToggleFavorite = { onToggleFavorite(event) }
//                            )
//                        }
//                    }
//                }
//
//                is Resource.Error -> {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(text = "Error: ${state.message}")
//                    }
//                }
//
//                is Resource.Loading -> {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.padding(16.dp),
//                            color = Color.Blue
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
