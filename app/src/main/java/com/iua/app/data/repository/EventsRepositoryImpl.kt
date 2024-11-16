package com.iua.app.data.repository

import com.iua.app.data.local.dao.EventDAO
import com.iua.app.data.local.entity.EventEntity
import com.iua.app.data.remote.api.EventsApi
import com.iua.app.domain.model.EventsModel
import com.iua.app.domain.model.toEventsEntity
import com.iua.app.domain.model.toEventsModel
import com.iua.app.domain.repository.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val eventDAO: EventDAO,
    private val eventsAPI: EventsApi
) : EventsRepository {


    override suspend fun getEvents(): MutableList<EventsModel> {
        return try {
            val eventsFromApi = eventsAPI.getEvents()

            // Guardar en la base de datos local
            eventsFromApi.forEach { event ->
                eventDAO.insertEvents(event.toEventsModel().toEventsEntity())
            }

            // Retornar desde la base de datos
            eventDAO.getEntity().map { it.toEventsModel() }.toMutableList()

        } catch (e: Exception) {
            // Si falla la API, usar solo los datos locales
            eventDAO.getEntity().map { it.toEventsModel() }.toMutableList()
        }
    }

    override suspend fun saveEvents(eventsModel: EventsModel): Boolean {
        try {
            val result = eventDAO.insertEvents(eventsModel.toEventsEntity())
            return result.toInt() != -1
        } catch (e: Exception) {
            throw e
        }
    }

    //    override suspend fun updateEvent(eventModel: EventsModel): Boolean {
//        return try {
//            val rowsUpdated = eventDAO.updateEvent(eventModel.toEventsEntity())
//            rowsUpdated > 0
//        } catch (e: Exception) {
//            false
//        }
//    }
    override suspend fun updateEvent(id: Long, isFavorite: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val rowsUpdated = eventDAO.updateFavoriteStatus(id, isFavorite)
                rowsUpdated > 0
            } catch (e: Exception) {
                false
            }
        }
    }


    override suspend fun getFavoriteEvents(): List<EventEntity> {
        return eventDAO.getFavoriteEvents()
    }


}