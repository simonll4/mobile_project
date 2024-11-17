package com.iua.app.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.usecase.GetFavoritesEventsUseCase
import com.iua.app.domain.usecase.UpdateFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteEventsUseCase: GetFavoritesEventsUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) : ViewModel() {

    private val _favoriteEvents =
        MutableStateFlow<Resource<MutableList<EventModel>>>(Resource.Loading())
    val favoriteEvents: StateFlow<Resource<MutableList<EventModel>>> = _favoriteEvents

    init {
        fetchFavoriteEvents()
    }

    fun refreshFavoriteEvents() {
        fetchFavoriteEvents()
    }

    fun fetchFavoriteEvents() {
        viewModelScope.launch {
            getFavoriteEventsUseCase()
                .catch { exception ->
                    _favoriteEvents.value =
                        Resource.Error(exception.localizedMessage ?: "Error al cargar favoritos")
                }
                .collect { resource ->
                    val favoriteOnly = (resource as? Resource.Success)
                        ?.data
                        ?.filter { it.isFavorite }
                        ?.toMutableList()

                    _favoriteEvents.value = if (favoriteOnly != null) {
                        Resource.Success(favoriteOnly)
                    } else {
                        resource as Resource<MutableList<EventModel>>
                    }

                }
        }
    }

    fun toggleFavorite(event: EventModel) {
        viewModelScope.launch {
            val updatedEvent = event.copy(isFavorite = !event.isFavorite)
            updateFavoriteStatusUseCase(event.id, updatedEvent.isFavorite).collect { result ->
                if (result is Resource.Success) {
                    val currentFavorites =
                        (_favoriteEvents.value as? Resource.Success)?.data ?: return@collect
                    val updatedList =
                        currentFavorites.filterNot { it.id == updatedEvent.id }.toMutableList()
                    _favoriteEvents.value = Resource.Success(updatedList)
                }
            }
        }
    }

}