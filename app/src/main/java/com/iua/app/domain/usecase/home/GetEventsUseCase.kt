package com.iua.app.domain.usecase.home

import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EventsRepository
) {

    operator fun invoke(): Flow<Resource<MutableList<EventModel>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(data = repository.getEvents()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }
}