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


}


