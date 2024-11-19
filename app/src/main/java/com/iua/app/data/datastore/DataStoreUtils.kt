package com.iua.app.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Nombre del DataStore
private const val PREFERENCES_NAME = "local_data_store"

val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

suspend fun setEventId(context: Context, eventId: String = "") {
    context.dataStore.edit { preferences ->
        preferences[DataStoreKeys.EVENT_ID] = eventId
    }
}

suspend fun isUserLoggedIn(context: Context): Boolean {
    val preferences = context.dataStore.data.first()
    return preferences[DataStoreKeys.IS_USER_LOGGED_IN] ?: false
}

suspend fun getEventId(context: Context): String {
    val preferences = context.dataStore.data.first()
    return (preferences[DataStoreKeys.EVENT_ID] ?: "")
}

suspend fun setImmediateWorkScheduled(context: Context, isScheduled: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[DataStoreKeys.IS_IMMEDIATE_WORK_SCHEDULED] = isScheduled
    }
}

suspend fun isImmediateWorkScheduled(context: Context): Boolean {
    val preferences = context.dataStore.data.first()
    return preferences[DataStoreKeys.IS_IMMEDIATE_WORK_SCHEDULED] ?: false
}


suspend fun setWorkScheduled(context: Context, isScheduled: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[DataStoreKeys.IS_WORK_SCHEDULED] = isScheduled
    }
}

suspend fun isWorkScheduled(context: Context): Boolean {
    val preferences = context.dataStore.data.first()
    return preferences[DataStoreKeys.IS_WORK_SCHEDULED] ?: false
}

