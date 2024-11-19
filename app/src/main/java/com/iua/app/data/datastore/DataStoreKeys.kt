package com.iua.app.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {

    // Preferences
    val DARK_MODE = booleanPreferencesKey("dark_mode")

    val EVENT_ID = stringPreferencesKey("event_id")


    // User
    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
    val USER_ID = stringPreferencesKey("user_id")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_LAST_NAME = stringPreferencesKey("user_last_name")

}