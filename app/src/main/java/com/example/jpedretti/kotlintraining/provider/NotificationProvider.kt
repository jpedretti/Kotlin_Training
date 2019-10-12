package com.example.jpedretti.kotlintraining.provider

import android.app.Notification
import androidx.annotation.DrawableRes

interface NotificationProvider {
    fun createChannel()

    fun createNotification(title: String, text: String,
                           @DrawableRes drawableId: Int): Notification

    fun notify(notificationId: Int, notification: Notification)

    fun createNotificationAndNotify(notificationId: Int, title: String, text: String,
                                    @DrawableRes drawableId: Int)
}