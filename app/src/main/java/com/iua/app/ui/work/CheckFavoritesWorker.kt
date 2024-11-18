package com.iua.app.ui.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.iua.app.R
import com.iua.app.domain.repository.EventsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CheckFavoritesWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val eventsRepository: EventsRepository
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            val eventsForTomorrow = eventsRepository.getFavoriteEventsForTomorrow()


            //Log.d("CheckFavoritesWorker", "Eventos favoritos de mañana: $eventsForTomorrow")
            if (eventsForTomorrow.isNotEmpty()) {
                showNotification(eventsForTomorrow.size)
            }

            Result.success()
        } catch (e: Exception) {
            Log.e("CheckFavoritesWorker", "Error: ${e.message}", e)
            Result.failure()
        }

    }

    private fun showNotification(eventCount: Int) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "favorites_channel"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(channelId) == null) {
                val channel = NotificationChannel(
                    channelId,
                    "Eventos favoritos",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Notificaciones de eventos favoritos"
                }
                notificationManager.createNotificationChannel(channel)
            }
        }

        // Crear y mostrar la notificación
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.event_notification)
            .setContentTitle("Eventos favoritos")
            .setContentText("Tienes $eventCount evento(s) favorito(s) mañana.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }

}