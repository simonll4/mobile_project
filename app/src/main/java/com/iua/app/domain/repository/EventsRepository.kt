package com.iua.app.domain.repository


import com.iua.app.data.local.entity.EventEntity
import com.iua.app.domain.model.EventsModel

interface EventsRepository {

    suspend fun getEvents(): MutableList<EventsModel>

    suspend fun saveEvents(eventsModel: EventsModel): Boolean

    suspend fun getFavoriteEvents(): List<EventEntity>

    suspend fun updateEvent(id: Long, isFavorite: Boolean): Boolean
}