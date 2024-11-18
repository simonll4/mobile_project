package com.iua.app.ui.view_models

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.App.Companion.dataStore
import com.iua.app.data.DataStoreKeys
import com.iua.app.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _user = MutableStateFlow<UserModel?>(null)
    val user: StateFlow<UserModel?> = _user

    init {
        viewModelScope.launch {
            readUserFromDataStore()
        }
    }

    // Keys for the data store
    private val userIdKey = DataStoreKeys.USER_ID
    private val userEmailKey = DataStoreKeys.USER_EMAIL
    private val userNameKey = DataStoreKeys.USER_NAME
    private val userLastNameKey = DataStoreKeys.USER_LAST_NAME

    private suspend fun readUserFromDataStore() {
        context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
        }.map { preferences ->
            val userId = preferences[userIdKey]
            val email = preferences[userEmailKey]
            val name = preferences[userNameKey]
            val lastName = preferences[userLastNameKey]

            if (userId != null && email != null && name != null && lastName != null) {
                UserModel(userId, name, lastName, email, "")
            } else {
                null
            }
        }.collect { userModel ->
            _user.value = userModel
            userModel?.let {
                Log.d(
                    "ProfileViewModel",
                    "User ID: ${it.id}, Name: ${it.firstName}, Last Name: ${it.lastName}, Email: ${it.email}"
                )
            }
        }
    }

    suspend fun clearUserDataFromDataStore() {
        context.dataStore.edit { preferences ->
            preferences.remove(DataStoreKeys.USER_ID)
            preferences.remove(DataStoreKeys.USER_EMAIL)
            preferences.remove(DataStoreKeys.USER_NAME)
            preferences.remove(DataStoreKeys.USER_LAST_NAME)
        }
        Log.d("ProfileViewModel", "Datos del usuario borrados de DataStore")
    }

}