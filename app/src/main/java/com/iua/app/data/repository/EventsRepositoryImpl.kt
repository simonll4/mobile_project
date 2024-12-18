package com.iua.app.data.repository

import android.util.Log
import com.iua.app.data.local.dao.EventDAO
import com.iua.app.data.remote.api.EventApi
import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.toEventsEntity
import com.iua.app.domain.model.toEventsModel
import com.iua.app.domain.repository.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val eventDAO: EventDAO,
    private val eventAPI: EventApi
) : EventsRepository {

    override suspend fun getEvents(): MutableList<EventModel> {
        return try {

            // Obtener los eventos desde la API
            val eventsFromApi = eventAPI.getEvents()

            // Obtener los eventos locales actuales
            val localEvents = eventDAO.getEvents().associateBy { it.id }

            // Preparar la lista para guardar en la base de datos
            val updatedEvents = eventsFromApi.map { eventFromApi ->
                val entity =
                    eventFromApi.toEventsModel().toEventsEntity() // Aquí se convierte la fecha
                localEvents[entity.id]?.let { localEvent ->
                    entity.isFavorite = localEvent.isFavorite
                }
                entity
            }

            // Guardar en la base de datos local
            eventDAO.insertEvents(updatedEvents)

            // Retornar desde la base de datos
            eventDAO.getEvents().map { it.toEventsModel() }.toMutableList()

        } catch (e: Exception) {
            Log.e("EventsRepositoryImpl", "getEvents: ${e.message}")
            // Si falla la API, usar solo los datos locales
            eventDAO.getEvents().map { it.toEventsModel() }.toMutableList()

        }
    }

    override suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val rowsUpdated = eventDAO.updateFavoriteStatus(id, isFavorite)
                rowsUpdated > 0
            } catch (e: Exception) {
                false
            }
        }
    }

    override suspend fun getFavoriteEvents(): MutableList<EventModel> {
        return eventDAO.getFavoriteEvents().map { it.toEventsModel() }.toMutableList()
    }

    override suspend fun getEventById(id: Long): EventModel? {
        return eventDAO.getEventById(id)?.toEventsModel()
    }

    override suspend fun getFavoriteEventsForTomorrow(): List<EventModel> {
        // Calcular la fecha de mañana en Date
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val tomorrowDate = calendar.time // Obtener la fecha de mañana como un objeto Date

        // Obtener los eventos favoritos de mañana desde el DAO
        val favoriteEvents = eventDAO.getFavoriteEventsForTomorrow(tomorrowDate)

        // Convertir las entidades a modelos y retornarlas
        return favoriteEvents.map { it.toEventsModel() }
    }

}