package com.example.jpedretti.kotlintraining

import android.app.Application
import android.app.NotificationManager
import android.arch.lifecycle.ViewModel
import android.support.v4.app.NotificationCompat
import kotlinx.coroutines.experimental.async
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*


class DIViewModel(private val testService: TestService,
                  private val notificationManager: NotificationManager)
    : ViewModel(), KoinComponent {

    private val application = inject<Application>()

    fun callDoTestServiceStuff() = async {
        val notification = NotificationCompat.Builder(application.value, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("DI")
                .setContentText("hello DIViewModel")
        notificationManager.notify(Random().nextInt(), notification.build())
        testService.doServiceStuffAsync().await()
    }
}