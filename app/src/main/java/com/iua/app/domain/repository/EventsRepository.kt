package com.iua.app.domain.repository


import com.iua.app.domain.model.EventsModel

interface EventsRepository {
    suspend fun getEvents(): MutableList<EventsModel>
    suspend fun saveEvents(eventsModel: EventsModel): Boolean
}