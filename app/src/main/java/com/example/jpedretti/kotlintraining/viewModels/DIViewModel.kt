package com.example.jpedretti.kotlintraining.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.models.DiModel
import com.example.jpedretti.kotlintraining.services.NotificationService
import com.example.jpedretti.kotlintraining.services.TestService
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*

@SuppressLint("StaticFieldLeak")
class DIViewModel(private val testService: TestService, private val application: Application,
                  private val notificationService: NotificationService) : ViewModel() {

    val model = DiModel()

    fun onCreate() {
        notificationService.createChannel()
        model.appName.set(application.resources.getString(R.string.app_name))
    }

    fun doServiceStuffByViewModelClick() {
        model.loading.set(true)
        launch(UI) {
            val serviceResult = callDoTestServiceStuff().await()
            model.testServiceDoStuffResult.set(serviceResult)
            model.loading.set(false)
        }
    }

    private fun callDoTestServiceStuff() = async {
        notificationService.createNotificationAndNotify(Random().nextInt(), "DI",
                getMessage(), R.drawable.ic_launcher_background)
        testService.doServiceStuffAsync().await()
    }

    private fun getMessage() =
            if (model.message.get().isNullOrBlank()) "no message" else model.message.get()!!
}