package com.example.jpedretti.kotlintraining.services

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.app.NotificationCompat

class NotificationServiceImpl(private val notificationManager: NotificationManager,
                              private val application: Application,
                              private val channelId: String) : NotificationService {

    override fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                    NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun createNotification(title: String, text: String,
                           @DrawableRes drawableId: Int) : Notification {
        return NotificationCompat.Builder(application, channelId)
                    .setSmallIcon(drawableId)
                    .setContentTitle(title)
                    .setContentText(text).build()

    }

    override fun notify(notificationId: Int, notification: Notification) {
        notificationManager.notify(notificationId, notification)
    }

    override fun createNotificationAndNotify(notificationId: Int, title: String, text: String,
                                   @DrawableRes drawableId: Int) {
        val notification = createNotification(title, text, drawableId)
        notify(notificationId, notification)
    }
}