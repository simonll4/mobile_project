package com.iua.app

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.iua.app.di.work.FavoriteEventReminderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: FavoriteEventReminderFactory

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG).setWorkerFactory(workerFactory)
            .build()
}