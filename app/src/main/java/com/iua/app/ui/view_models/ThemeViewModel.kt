package com.iua.app.ui.view_models

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.data.datastore.DataStoreKeys
import com.iua.app.data.datastore.dataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class ThemeViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: Flow<Boolean> = _isDarkMode

    init {
        viewModelScope.launch {
            val preferences = application.dataStore.data.first()
            val isDarkModeFromPrefs = preferences[DataStoreKeys.DARK_MODE]

            val systemDarkMode = isSystemInDarkTheme(application)
            _isDarkMode.value = isDarkModeFromPrefs ?: systemDarkMode

            if (isDarkModeFromPrefs == null) {
                updateDarkMode(systemDarkMode)
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            _isDarkMode.value = enabled
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[DataStoreKeys.DARK_MODE] = enabled
            }
        }
    }

    private fun updateDarkMode(enabled: Boolean) {
        _isDarkMode.value = enabled
        viewModelScope.launch {
            getApplication<Application>().dataStore.edit { preferences ->
                preferences[DataStoreKeys.DARK_MODE] = enabled
            }
        }
    }

    private fun isSystemInDarkTheme(application: Application): Boolean {
        val uiMode = application.resources.configuration.uiMode
        val nightModeFlag = uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlag == android.content.res.Configuration.UI_MODE_NIGHT_YES
    }
}