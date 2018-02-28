package com.example.jpedretti.kotlintraining.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.infrastructure.CoroutineContextInjector
import com.example.jpedretti.kotlintraining.models.DiModel
import com.example.jpedretti.kotlintraining.manager.NotificationManager
import com.example.jpedretti.kotlintraining.manager.SwapiManager
import com.example.jpedretti.kotlintraining.manager.TestManager
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*

class DIViewModel(application: Application,
                  private val testManager: TestManager,
                  private val notificationManager: NotificationManager,
                  private val swapiManager: SwapiManager) : AndroidViewModel(application) {

    val model = DiModel()

    fun onCreate() {
        notificationManager.createChannel()
        model.appName.set(getApplication<Application>().getString(R.string.app_name))
    }

    fun doServiceStuffByViewModelClick() {
        model.loading.set(true)
        launch(CoroutineContextInjector.uiContext) {
            val serviceResult = callDoTestServiceStuff().await()
            model.testServiceDoStuffResult.set(serviceResult)
            model.loading.set(false)
        }
    }

    fun getPlanetsClicked() {
        model.loading.set(true)
        launch(CoroutineContextInjector.uiContext) {
            val planetsResult = swapiManager.getPlanetsAsync().await()
            if (planetsResult != null) {
                model.planets.addAll(planetsResult.results)
            }
            model.loading.set(false)
        }
    }

    private fun callDoTestServiceStuff() = async {
        notificationManager.createNotificationAndNotify(Random().nextInt(), "DI",
                getMessage(), R.drawable.ic_launcher_background)
        testManager.doServiceStuffAsync().await()
    }

    private fun getMessage() =
            if (model.message.get().isNullOrBlank()) "no message" else model.message.get()!!
}