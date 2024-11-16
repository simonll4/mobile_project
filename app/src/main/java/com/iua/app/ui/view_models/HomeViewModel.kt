package com.iua.app.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.model.EventsModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.usecase.GetEventsUseCase
import com.iua.app.domain.usecase.UpdateEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val updateEventsUseCase: UpdateEventsUseCase
) : ViewModel() {


    private val _events = MutableStateFlow<Resource<MutableList<EventsModel>>>(Resource.Loading())
    val events: StateFlow<Resource<MutableList<EventsModel>>> = _events

    private val _favorites = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val favorites: StateFlow<Map<String, Boolean>> = _favorites

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            getEventsUseCase().collect { resource ->
                _events.value = resource
            }
        }
    }

    fun toggleFavorite(event: EventsModel) {
        viewModelScope.launch {
            val updatedEvent = event.copy(isFavorite = !event.isFavorite)
            updateEventsUseCase(event.id, updatedEvent.isFavorite).collect { result ->

                if (result is Resource.Success) {
                    _events.value = when (val currentEvents = _events.value) {
                        is Resource.Success -> {
                            val updatedList = currentEvents.data?.toMutableList()?.apply {
                                val index = indexOfFirst { it.id == updatedEvent.id }
                                if (index != -1) this[index] = updatedEvent
                            }
                            Resource.Success(updatedList)
                        }

                        else -> currentEvents
                    }
                }

            }
        }
    }

}