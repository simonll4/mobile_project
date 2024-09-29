package com.iua.app.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.model.EventsModel
import com.iua.app.domain.usecase.GetEventsUseCase
import com.iua.app.domain.usecase.SaveEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase, private val saveEventsUseCase: SaveEventsUseCase
) : ViewModel() {

    init {
        saveEvent(EventsModel("1", "Evento 1"))
    }

    fun saveEvent(event: EventsModel) {
        viewModelScope.launch {
            saveEventsUseCase(event).collect { resource ->
                println(resource)
            }
        }
    }

    suspend fun getEvents() {
        viewModelScope.launch {
            getEventsUseCase().collect { resource ->
                println(resource)
            }

        }
    }
}


