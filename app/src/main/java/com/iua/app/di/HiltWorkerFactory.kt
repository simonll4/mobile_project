//package com.iua.app.di
//
//import android.content.Context
//import androidx.work.ListenableWorker
//import androidx.work.WorkerFactory
//import androidx.work.WorkerParameters
//import com.iua.app.domain.repository.EventsRepository
//import com.iua.app.ui.work.CheckFavoritesWorker
//import javax.inject.Inject
//import javax.inject.Provider
//
//class HiltWorkerFactory @Inject constructor(
//    private val workerProviders: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<CheckFavoritesWorker.Factory>>,
//    private val eventsRepository: EventsRepository
//) : WorkerFactory() {
//
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters
//    ): ListenableWorker? {
//        val foundEntry = workerProviders.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
//        val factoryProvider = foundEntry?.value ?: return null
//        return factoryProvider.get().create(appContext, workerParameters, eventsRepository)
//    }
//}