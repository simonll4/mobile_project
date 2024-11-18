package com.iua.app.domain.repository

import com.iua.app.domain.model.EventModel

interface EventsRepository {

    suspend fun getEvents(): MutableList<EventModel>

    suspend fun getFavoriteEvents(): MutableList<EventModel>

    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean): Boolean

    suspend fun getEventById(id: Long): EventModel?

    suspend fun getFavoriteEventsForTomorrow(): List<EventModel>
}