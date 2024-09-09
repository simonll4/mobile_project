package com.iua.app.data.repository

import com.iua.app.data.local.dao.EntityDao
import com.iua.app.domain.model.EventsModel
import com.iua.app.domain.model.toEventsEntity
import com.iua.app.domain.repository.EventsRepository
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val entityDao: EntityDao
) : EventsRepository {

    override suspend fun getEvents(): MutableList<EventsModel>{
       try {
        return  entityDao.getEntity().map {
            EventsModel(
                name = it.name,
                description = it.description
            )
        }.toMutableList()
       }catch (e: Exception){
           throw e
       }
    }

    override suspend fun saveEvents(eventsModel: EventsModel): Boolean {
        try {
            val result = entityDao.insertEvents(eventsModel.toEventsEntity())
            return result.toInt() != -1
        }catch (e: Exception){
            throw  e
        }
    }

}