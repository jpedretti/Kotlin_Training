package com.example.jpedretti.kotlintraining.viewModels

import android.arch.lifecycle.ViewModel
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.injection.CoroutineContextInjector
import com.example.jpedretti.kotlintraining.models.DiModel
import com.example.jpedretti.kotlintraining.services.NotificationService
import com.example.jpedretti.kotlintraining.services.ResourcesService
import com.example.jpedretti.kotlintraining.services.api.SwapiPlanetService
import com.example.jpedretti.kotlintraining.services.TestService
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*

class DIViewModel(private val testService: TestService,
                  private val resourcesService: ResourcesService,
                  private val notificationService: NotificationService,
                  private val swapiPlanetService: SwapiPlanetService) : ViewModel() {

    val model = DiModel()

    fun onCreate() {
        notificationService.createChannel()
        model.appName.set(resourcesService.getString(R.string.app_name))
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
            val planetsResult = swapiPlanetService.getPlanetsAsync().await()
            if (planetsResult != null) {
                model.planets.addAll(planetsResult.results)
            }
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