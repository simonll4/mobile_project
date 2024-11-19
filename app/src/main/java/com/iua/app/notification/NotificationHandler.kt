package com.iua.app.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.iua.app.R
import com.iua.app.ui.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHandler @Inject constructor(private val context: Context) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotificationChannel(channelId: String, channelName: String, description: String) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(channelId) == null) {
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    this.description = description
                }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun sendEventNotification(
        channelId: String,
        eventId: Long,
        title: String,
        content: String,
        notificationId: Int
    ) {
        // Crear Intent para abrir la pantalla de detalles del evento
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("eventId", eventId.toString()) // Pasar el ID del evento
        }

        // Crear un PendingIntent para manejar el clic
        val pendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(notificationId, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)!!
        }

        // Crear la notificación
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.event_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        // Mostrar la notificación
        notificationManager.notify(notificationId, notification)
    }
}



//package com.iua.app.notification
//
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import androidx.core.app.NotificationCompat
//import com.iua.app.R
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class NotificationHandler @Inject constructor(private val context: Context) {
//
//    private val notificationManager =
//        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//    fun createNotificationChannel(channelId: String, channelName: String, description: String) {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            if (notificationManager.getNotificationChannel(channelId) == null) {
//                val channel = NotificationChannel(
//                    channelId,
//                    channelName,
//                    NotificationManager.IMPORTANCE_DEFAULT
//                ).apply {
//                    this.description = description
//                }
//                notificationManager.createNotificationChannel(channel)
//            }
//        }
//    }
//
//    fun sendNotification(channelId: String, title: String, content: String, notificationId: Int) {
//        val notification = NotificationCompat.Builder(context, channelId)
//            .setSmallIcon(R.drawable.event_notification)
//            .setContentTitle(title)
//            .setContentText(content)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .build()
//
//        notificationManager.notify(notificationId, notification)
//    }
//}
