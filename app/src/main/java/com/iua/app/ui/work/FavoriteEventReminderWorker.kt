package com.iua.app.ui.work

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.iua.app.domain.repository.EventsRepository
import com.iua.app.notification.NotificationHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class FavoriteEventReminderWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val eventsRepository: EventsRepository,
    private val notificationHandler: NotificationHandler
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val CHANNEL_ID = "events_favorites_reminder"
        private const val CHANNEL_NAME = "Eventos favoritos"
        private const val CHANNEL_DESCRIPTION = "Notificaciones de eventos favoritos"
    }

    override suspend fun doWork(): Result {
        return try {
            val eventsForTomorrow = eventsRepository.getFavoriteEventsForTomorrow()

            if (eventsForTomorrow.isNotEmpty()) {
                notificationHandler.createNotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    CHANNEL_DESCRIPTION
                )

                notificationHandler.sendNotification(
                    CHANNEL_ID,
                    "Eventos favoritos",
                    "Tienes ${eventsForTomorrow.size} evento(s) favorito(s) mañana.",
                    1
                )
            }

            Result.success()
        } catch (e: Exception) {
            Log.e("CheckFavoritesWorker", "Error: ${e.message}", e)
            Result.failure()
        }
    }
}