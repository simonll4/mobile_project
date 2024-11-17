package com.iua.app.domain.usecase

import com.iua.app.domain.model.Resource
import com.iua.app.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateFavoriteStatusUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    operator fun invoke(id: Long, isFavorite: Boolean): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.updateFavoriteStatus(id, isFavorite)
            emit(Resource.Success(data = result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }
}