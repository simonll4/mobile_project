package com.iua.app.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.model.EventModel
import com.iua.app.domain.model.Resource
import com.iua.app.domain.usecase.GetEventByIdUseCase
import com.iua.app.domain.usecase.UpdateFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) : ViewModel() {

    private val _event = MutableStateFlow<Resource<EventModel>?>(null)
    val event: StateFlow<Resource<EventModel>?> = _event

    private val _favoriteUpdateStatus = MutableStateFlow<Resource<Boolean>?>(null)

    fun loadEvent(eventId: Long) {
        viewModelScope.launch {
            getEventByIdUseCase(eventId).collect { result ->
                _event.value = result
            }
        }
    }

    fun toggleFavorite(event: EventModel?) {
        viewModelScope.launch {
            event?.let { it ->
                _favoriteUpdateStatus.value = Resource.Loading()
                updateFavoriteStatusUseCase(it.id, !it.isFavorite).collect { result ->
                    _favoriteUpdateStatus.value = result
                    if (result is Resource.Success) {
                        _event.value = _event.value?.let {
                            val updatedEvent = it.data?.copy(isFavorite = !it.data.isFavorite)
                            Resource.Success(updatedEvent)
                        }
                    }
                }
            }
        }
    }
}