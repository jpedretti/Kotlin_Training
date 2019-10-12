package com.example.jpedretti.kotlintraining.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpedretti.kotlintraining.R
import com.example.jpedretti.kotlintraining.business.SwapiBusiness
import com.example.jpedretti.kotlintraining.business.TestBusiness
import com.example.jpedretti.kotlintraining.models.DiModel
import com.example.jpedretti.kotlintraining.provider.NotificationProvider
import kotlinx.coroutines.*
import java.util.*

class DIViewModel(application: Application,
                  private val testBusiness: TestBusiness,
                  private val notificationProvider: NotificationProvider,
                  private val swapiBusiness: SwapiBusiness) : AndroidViewModel(application) {

    val model = DiModel()

    fun onCreate() {
        notificationProvider.createChannel()
        model.appName.value = getApplication<Application>().getString(R.string.app_name)
    }

    fun doServiceStuffByViewModelClick() {
        model.loading.value = true
        viewModelScope.launch {
            val serviceResult = callDoTestServiceStuffAsync().await()
            model.testServiceDoStuffResult.value = serviceResult
            model.loading.value = false
        }
    }

    fun getPlanetsClicked() {
        model.loading.value = true
        viewModelScope.launch {
            val planetsResult = swapiBusiness.getPlanetsAsync().await()
            if (planetsResult != null) {
                model.planets.value = planetsResult.results
            }
            model.loading.value = false
        }
    }

    private fun callDoTestServiceStuffAsync() = viewModelScope.async {
        notificationProvider.createNotificationAndNotify(Random().nextInt(),
                "May the force be with you!", getMessage(), R.drawable.ic_launcher_background)
        testBusiness.doServiceStuffAsync().await()
    }

    private fun getMessage() =
            if (model.message.value.isNullOrBlank()) "no message" else model.message.value!!
}