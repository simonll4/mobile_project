package com.iua.app.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.mock.Event
import com.iua.app.mock.eventsMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    private val getEventsUseCase: GetEventsUseCase, private val saveEventsUseCase: SaveEventsUseCase
//) : ViewModel() {}

class HomeViewModel @Inject constructor() : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    private val _favorites = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val favorites: StateFlow<Map<String, Boolean>> = _favorites

    init {
        loadMockEvents()
    }

    private fun loadMockEvents() {
        viewModelScope.launch {
            _events.value = eventsMock
        }
    }

    fun toggleFavorite(eventId: String) {
        val currentFavorites = _favorites.value.toMutableMap()
        val isFavorite = currentFavorites[eventId] ?: false
        currentFavorites[eventId] = !isFavorite
        _favorites.value = currentFavorites
    }
}