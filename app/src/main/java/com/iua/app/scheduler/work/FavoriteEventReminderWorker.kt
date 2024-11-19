package com.iua.app.scheduler.work

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.iua.app.domain.repository.EventsRepository
import com.iua.app.notification.NotificationHandler
import com.iua.app.ui.screens.home.toFormattedTime
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val hasPermission = ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if (!hasPermission) {
                    Log.e("EventReminderWorker", "Permiso de notificaciones no otorgado")
                    return Result.failure() // O maneja esto de otra manera
                }
            }

            val eventsForTomorrow = eventsRepository.getFavoriteEventsForTomorrow()

            Log.d("EventReminderWorker", "Events for tomorrow: $eventsForTomorrow")
            if (eventsForTomorrow.isNotEmpty()) {
                notificationHandler.createNotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    CHANNEL_DESCRIPTION
                )

                // Generar una notificación por cada evento
                eventsForTomorrow.forEachIndexed { index, event ->
                    val title = "Evento mañana: ${event.title}"
                    val content = "Hora: ${event.date.toFormattedTime()} en ${event.location}"

                    notificationHandler.sendEventNotification(
                        CHANNEL_ID,
                        event.id,
                        title,
                        content,
                        event.id.toInt()
                    )
                }
            }

            Result.success()
        } catch (e: Exception) {
            Log.e("EventReminderWorker", "Error: ${e.message}", e)
            Result.failure()
        }
    }
}