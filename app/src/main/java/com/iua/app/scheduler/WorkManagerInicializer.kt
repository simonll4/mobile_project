package com.iua.app.scheduler

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.iua.app.data.datastore.isImmediateWorkScheduled
import com.iua.app.data.datastore.setImmediateWorkScheduled
import com.iua.app.scheduler.work.FavoriteEventReminderWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkManagerInitializer @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val UNIQUE_PERIODIC_WORK_NAME = "FavoriteEventReminderWorker"
        private const val UNIQUE_IMMEDIATE_WORK_NAME = "FavoriteEventReminderWorkerImmediate"
    }

    /**
     * Configura un PeriodicWorkRequest para que se ejecute periodicamente.
     */
    fun setupPeriodicWorkIfNeeded() {
        val workManager = WorkManager.getInstance(context)
        workManager.getWorkInfosForUniqueWork(UNIQUE_PERIODIC_WORK_NAME)
            .get().let { workInfos ->
                if (workInfos.isNullOrEmpty()) {
                    val periodicWorkRequest =
                        PeriodicWorkRequestBuilder<FavoriteEventReminderWorker>(
                            1, TimeUnit.DAYS // Intervalo de 1 día
                        ).setConstraints(
                            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                                .setRequiresBatteryNotLow(true).build()
                        ).build()
                    workManager.enqueueUniquePeriodicWork(
                        UNIQUE_PERIODIC_WORK_NAME,
                        ExistingPeriodicWorkPolicy.KEEP, // No reemplazar si ya existe
                        periodicWorkRequest
                    )
                }
            }
    }

    /**
     * Configura un OneTimeWorkRequest para que se ejecute
     * inmediatamente una sola vez (con un pequeño retraso inicial).
     */
    fun scheduleImmediateCheck() {
        val workManager = WorkManager.getInstance(context)
        CoroutineScope(Dispatchers.IO).launch {
            // Verificar en DataStore si el trabajo inmediato ya está programado
            if (!isImmediateWorkScheduled(context)) {
                val workRequest = OneTimeWorkRequestBuilder<FavoriteEventReminderWorker>()
                    .setInitialDelay(300, TimeUnit.SECONDS)
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .setRequiresBatteryNotLow(true)
                            .build()
                    )
                    .build()

                // Programar el trabajo único
                workManager.enqueueUniqueWork(
                    UNIQUE_IMMEDIATE_WORK_NAME,
                    ExistingWorkPolicy.KEEP, // No reemplazar si ya existe
                    workRequest
                )

                // Marcar el trabajo como programado en DataStore
                setImmediateWorkScheduled(context, true)
            }
        }
    }

//    fun scheduleImmediateCheck() {
//        val workRequest = OneTimeWorkRequestBuilder<FavoriteEventReminderWorker>()
//            .setInitialDelay(10, TimeUnit.SECONDS)
//            .setConstraints(
//                Constraints.Builder()
//                    .setRequiredNetworkType(NetworkType.CONNECTED)
//                    .setRequiresBatteryNotLow(true)
//                    .build()
//            )
//            .build()
//        WorkManager.getInstance(context).enqueueUniqueWork(
//            UNIQUE_IMMEDIATE_WORK_NAME,
//            ExistingWorkPolicy.KEEP,
//            workRequest
//        )
//    }
}


