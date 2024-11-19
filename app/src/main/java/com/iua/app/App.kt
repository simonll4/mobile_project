package com.iua.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import androidx.work.Configuration
import com.iua.app.di.work.FavoriteEventReminderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    companion object {
        val Context.dataStore by preferencesDataStore(name = "user_prefs")
    }

    @Inject
    lateinit var workerFactory: FavoriteEventReminderFactory

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()

}