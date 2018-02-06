package com.example.jpedretti.kotlintraining

import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import kotlinx.coroutines.experimental.async
import java.util.*


class DIViewModel(private val testService: TestService,
                  override val kodein: Kodein) : KodeinAware {

    private val notificationManager : NotificationManager = instance()
    private val context: Context = instance()

    fun callDoTestServiceStuff() = async {
        val notification = NotificationCompat.Builder(this@DIViewModel.context, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("DI")
                .setContentText("hello DIViewModel")
        notificationManager.notify(Random().nextInt(), notification.build())
        testService.doServiceStuffAsync().await()
    }
}