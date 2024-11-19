//package com.iua.app.di.work
//
//import android.content.Context
//import androidx.work.*
//import com.iua.app.data.datastore.isImmediateWorkScheduled
//import com.iua.app.data.datastore.setImmediateWorkScheduled
//import com.iua.app.scheduler.work.FavoriteEventReminderWorker
//import dagger.hilt.android.qualifiers.ApplicationContext
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class WorkManagerConfigurator @Inject constructor(
//    @ApplicationContext private val context: Context
//) {
//
//    companion object {
//        private const val UNIQUE_PERIODIC_WORK_NAME = "FavoriteEventReminderWorker"
//        private const val UNIQUE_IMMEDIATE_WORK_NAME = "FavoriteEventReminderWorkerImmediate"
//    }
//
//    /**
//     * Configura un PeriodicWorkRequest para que se ejecute cada 15 minutos si no está configurado.
//     */
//    fun setupPeriodicWorkIfNeeded() {
//        val workManager = WorkManager.getInstance(context)
//        workManager.getWorkInfosForUniqueWork(UNIQUE_PERIODIC_WORK_NAME).get().let { workInfos ->
//            if (workInfos.isNullOrEmpty()) {
//                val periodicWorkRequest = PeriodicWorkRequestBuilder<FavoriteEventReminderWorker>(
//                    15, TimeUnit.MINUTES
//                ).setConstraints(
//                    Constraints.Builder()
//                        .setRequiredNetworkType(NetworkType.CONNECTED)
//                        .setRequiresBatteryNotLow(true)
//                        .build()
//                ).build()
//                workManager.enqueueUniquePeriodicWork(
//                    UNIQUE_PERIODIC_WORK_NAME,
//                    ExistingPeriodicWorkPolicy.KEEP, // No reemplazar si ya existe
//                    periodicWorkRequest
//                )
//            }
//        }
//    }
//
//    /**
//     * Configura un OneTimeWorkRequest para que se ejecute inmediatamente y una sola vez (con un pequeño retraso inicial).
//     */
//    fun scheduleImmediateCheck() {
//        val workManager = WorkManager.getInstance(context)
//        CoroutineScope(Dispatchers.IO).launch {
//            // Verificar en DataStore si el trabajo inmediato ya está programado
//            val isScheduled = isImmediateWorkScheduled(context)
//            if (!isScheduled) {
//                val workRequest = OneTimeWorkRequestBuilder<FavoriteEventReminderWorker>()
//                    .setInitialDelay(10, TimeUnit.SECONDS) // Retraso inicial de 10 segundos
//                    .setConstraints(
//                        Constraints.Builder()
//                            .setRequiredNetworkType(NetworkType.CONNECTED)
//                            .setRequiresBatteryNotLow(true)
//                            .build()
//                    )
//                    .build()
//                workManager.enqueueUniqueWork(
//                    UNIQUE_IMMEDIATE_WORK_NAME,
//                    ExistingWorkPolicy.REPLACE,
//                    workRequest
//                )
//                // Marcar el trabajo como programado en DataStore
//                setImmediateWorkScheduled(context, true)
//            }
//        }
//    }
//
//}
//
