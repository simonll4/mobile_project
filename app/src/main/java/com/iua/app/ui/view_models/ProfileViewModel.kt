package com.iua.app.ui.view_models

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.data.datastore.DataStoreKeys
import com.iua.app.data.datastore.dataStore
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

    fun sendHelpEmail(context: Context) {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822" // Filtra aplicaciones de correo electrónico
            putExtra(Intent.EXTRA_EMAIL, arrayOf("simon.llamosas44@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Consulta de Ayuda")
            putExtra(
                Intent.EXTRA_TEXT,
                "Por favor, describe tu problema aquí."
            ) // Opcional, cuerpo del mensaje
        }
        try {
            context.startActivity(
                Intent.createChooser(emailIntent, "Selecciona una aplicación de correo")
            )
        } catch (e: Exception) {
            Toast.makeText(context, "No hay aplicaciones de correo instaladas", Toast.LENGTH_LONG)
                .show()
        }
    }

    // llamada desde el boton logout
    suspend fun clearUserDataFromDataStore() {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_USER_LOGGED_IN] = false // Marca como deslogueado
            preferences.remove(DataStoreKeys.IS_IMMEDIATE_WORK_SCHEDULED)
            preferences.remove(DataStoreKeys.USER_ID)
            preferences.remove(DataStoreKeys.USER_EMAIL)
            preferences.remove(DataStoreKeys.USER_NAME)
            preferences.remove(DataStoreKeys.USER_LAST_NAME)
        }
        Log.d("ProfileViewModel", "Datos del usuario borrados de DataStore")
    }

}