package com.iua.app.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.iua.app.ui.components.TopAppBarComponent
import com.iua.app.ui.view_models.EventDetailViewModel

@Composable
fun EventDetailScreen(
    navController: NavHostController,
    eventId: String,
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    // Load the event using the eventId
    viewModel.loadEvent(eventId)
    val event by viewModel.event.collectAsState()

    event?.let {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            topBar = {
                TopAppBarComponent(
                    navController,
                    event!!.title,
                    Icons.AutoMirrored.Filled.ArrowBack
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .align(Alignment.TopCenter)
                ) {
                    EventDetailContent(
                        eventTitle = it.subtitle,
                        //TODO: Replace with actual event date and time
                        eventDate = "2024-01-01",
                        eventTime = "18:00",
                        eventVenue = "Sample Venue",
                        eventDescription = it.description,
                        eventImage = it.image,
                        onFavoriteClick = { /* TODO: Add logic to the favourites button */ }
                    )
                }
            }
        }
    }
}

@Composable
fun EventDetailContent(
    eventTitle: String,
    eventDate: String,
    eventTime: String,
    eventVenue: String,
    eventDescription: String,
    eventImage: String,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        // Event image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = eventImage), // Replace with your image
                contentDescription = "Event Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Event details
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = eventTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Event Date",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = eventDate,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = eventVenue,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Text(
                    text = eventTime,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Event description
        Text(
            text = eventDescription,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Add to favorites button
        Button(
            onClick = onFavoriteClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Add to Favorites",
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}