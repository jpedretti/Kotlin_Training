package com.example.jpedretti.kotlintraining.manager

import android.app.Notification
import android.support.annotation.DrawableRes

interface NotificationManager {
    fun createChannel()

    fun createNotification(title: String, text: String,
                           @DrawableRes drawableId: Int): Notification

    fun notify(notificationId: Int, notification: Notification)

    fun createNotificationAndNotify(notificationId: Int, title: String, text: String,
                                    @DrawableRes drawableId: Int)
}