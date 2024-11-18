package com.iua.app.work

import android.content.Context
import androidx.work.*
import com.iua.app.ui.work.CheckFavoritesWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkManagerInitializer @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun scheduleImmediateCheck() {
        val workRequest = OneTimeWorkRequestBuilder<CheckFavoritesWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "CheckFavoritesWorker",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}

//    fun scheduleDailyCheck() {
//        val workRequest = PeriodicWorkRequestBuilder<CheckFavoritesWorker>(1, TimeUnit.DAYS)
//            .setConstraints(
//                Constraints.Builder()
//                    .setRequiredNetworkType(NetworkType.CONNECTED)
//                    .setRequiresBatteryNotLow(true)
//                    .build()
//            )
//            .build()
//
//        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//            "CheckFavoritesWorker",
//            ExistingPeriodicWorkPolicy.UPDATE,
//            workRequest
//        )
//    }
