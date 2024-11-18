package com.iua.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.iua.app.domain.repository.EventsRepository
import com.iua.app.ui.work.CheckFavoritesWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

//@HiltAndroidApp
//class App : Application(){
//    companion object {
//        val Context.dataStore by preferencesDataStore(name = "user_prefs")
//    }
//}

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    companion object {
        val Context.dataStore by preferencesDataStore(name = "user_prefs")
    }

    @Inject
    lateinit var workerFactory: CheckFavoritesWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()

}

class CheckFavoritesWorkerFactory @Inject constructor(private val eventsRepository: EventsRepository) :
    WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = CheckFavoritesWorker(appContext, workerParameters, eventsRepository)

}
