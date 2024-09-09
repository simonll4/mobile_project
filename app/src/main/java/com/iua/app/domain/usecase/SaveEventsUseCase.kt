package com.iua.app.domain.usecase

import com.iua.app.domain.model.EventsModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveEventsUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    operator fun invoke(eventsModel: EventsModel): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(data = repository.saveEvents(eventsModel)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }
}