package com.iua.app.domain.usecase.home

import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    operator fun invoke(eventId: Long): Flow<Resource<EventModel>> = flow {
        try {
            emit(Resource.Loading())
            val event = repository.getEventById(eventId)
            if (event != null) {
                emit(Resource.Success(event))
            } else {
                emit(Resource.Error("Event not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }
}
