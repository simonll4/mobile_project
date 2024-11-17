package com.iua.app.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.usecase.GetEventsUseCase
import com.iua.app.domain.usecase.UpdateFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) : ViewModel() {

    private val _events = MutableStateFlow<Resource<MutableList<EventModel>>>(Resource.Loading())
    val events: StateFlow<Resource<MutableList<EventModel>>> = _events

    init {
        fetchEvents()
    }

    fun refreshEvents() {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            getEventsUseCase()
                .catch { exception ->
                    _events.value =
                        Resource.Error(exception.localizedMessage ?: "Error al cargar eventos")
                }
                .collect { resource ->
                    _events.value = resource
                }
        }
    }

    fun toggleFavorite(event: EventModel) {
        viewModelScope.launch {
            val updatedEvent = event.copy(isFavorite = !event.isFavorite)
            updateFavoriteStatusUseCase(event.id, updatedEvent.isFavorite).collect { result ->
                if (result is Resource.Success) {
                    val currentEvents = (_events.value as? Resource.Success)?.data ?: return@collect
                    val updatedList = currentEvents.map {
                        if (it.id == updatedEvent.id) updatedEvent else it
                    }
                    _events.value = Resource.Success(updatedList.toMutableList())
                }
            }
        }
    }

}