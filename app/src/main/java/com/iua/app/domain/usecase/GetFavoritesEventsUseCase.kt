package com.iua.app.domain.usecase

import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.repository.EventsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavoritesEventsUseCase @Inject constructor(
    private val repository: EventsRepository
) {

    operator fun invoke(): Flow<Resource<List<EventModel>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(data = repository.getFavoriteEvents()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error fetching favorite events"))
        }
    }


}