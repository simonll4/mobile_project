package com.iua.app.di.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.iua.app.domain.repository.EventsRepository
import com.iua.app.notification.NotificationHandler
import com.iua.app.scheduler.work.FavoriteEventReminderWorker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteEventReminderFactory @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val notificationHandler: NotificationHandler
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            FavoriteEventReminderWorker::class.java.name ->
                FavoriteEventReminderWorker(
                    appContext,
                    workerParameters,
                    eventsRepository,
                    notificationHandler
                )

            else -> null
        }
    }
}
