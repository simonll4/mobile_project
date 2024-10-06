package com.iua.app.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.mock.Event
import com.iua.app.mock.eventsMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventDetailViewModel @Inject constructor() : ViewModel() {

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?> = _event

    fun loadEvent(eventId: String) {
        viewModelScope.launch {
            _event.value = eventsMock.find { it.id == eventId }
        }
    }
}