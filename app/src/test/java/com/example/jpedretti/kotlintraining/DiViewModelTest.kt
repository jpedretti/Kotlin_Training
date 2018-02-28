package com.example.jpedretti.kotlintraining

import android.app.Application
import com.example.jpedretti.kotlintraining.manager.NotificationManager
import com.example.jpedretti.kotlintraining.manager.TestManager
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import com.example.jpedretti.kotlintraining.manager.SwapiManager
import com.example.jpedretti.kotlintraining.viewModel.DIViewModel
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiViewModelTest {

    @Mock
    private lateinit var application: Application
    @Mock
    private lateinit var testManager: TestManager
    @Mock
    private lateinit var notificationManager: NotificationManager
    @Mock
    private lateinit var swapiManager: SwapiManager
    private lateinit var viewModel: DIViewModel

    @Before
    fun setup() {
        viewModel = DIViewModel(application, testManager, notificationManager, swapiManager)
    }

    @Test
    fun dIViewModel_SuccessfullyInit() {
        org.mockito.Mockito.`when`(application.getString(R.string.app_name))
                .thenReturn("KotlinTraining")

        viewModel.onCreate()

        assertEquals("KotlinTraining", viewModel.model.appName.get())
        verify(notificationManager, times(1)).createChannel()
        verify(testManager, times(0)).doServiceStuffAsync()
        verify(notificationManager, times(0))
                .createNotificationAndNotify(anyInt(), anyString(),anyString(),anyInt())
    }

    @Test
    fun dIViewModel_SuccessfullyDoServiceStuff() {
        `when`(testManager.doServiceStuffAsync())
                .thenReturn(CompletableDeferred("May the force be with you!"))

        viewModel.onCreate()
        runBlocking {
            viewModel.doServiceStuffByViewModelClick()
        }

        verify(notificationManager,times(1))
                .createNotificationAndNotify(anyInt(), anyString(),anyString(),anyInt())
        verify(testManager, times(1)).doServiceStuffAsync()
        assertEquals("May the force be with you!",
                viewModel.model.testServiceDoStuffResult.get())
    }

    @Test
    fun diViewModel_SuccessfullyGetPlanets() {
        val result = getPlanetsResult()

        `when`(swapiManager.getPlanetsAsync())
                .thenReturn(CompletableDeferred(result))

        viewModel.onCreate()
        runBlocking {
            viewModel.getPlanetsClicked()
        }

        verify(testManager, times(0)).doServiceStuffAsync()
        verify(notificationManager, times(0))
                .createNotificationAndNotify(anyInt(), anyString(),anyString(),anyInt())
        verify(swapiManager, times(1))
                .getPlanetsAsync()
        assertEquals(2, viewModel.model.planets.size)

        assertEquals("Alderaan", viewModel.model.planets[0].name)
        assertEquals("1", viewModel.model.planets[0].orbitalPeriod)
        assertEquals("2", viewModel.model.planets[0].rotationPeriod)

        assertEquals("Yavin IV", viewModel.model.planets[1].name)
        assertEquals("4", viewModel.model.planets[1].orbitalPeriod)
        assertEquals("5", viewModel.model.planets[1].rotationPeriod)
    }

    private fun getPlanetsResult(): SwapiResult<PlanetResult> {
        val result = SwapiResult<PlanetResult>().apply {
            this.count = 2
            this.nextPage = "2"
            this.previousPage = null
            this.results = ArrayList()
        }

        (result.results as ArrayList).add(PlanetResult().apply {
            this.name = "Alderaan"
            this.orbitalPeriod = "1"
            this.rotationPeriod = "2"
        })

        (result.results as ArrayList).add(PlanetResult().apply {
            this.name = "Yavin IV"
            this.orbitalPeriod = "4"
            this.rotationPeriod = "5"
        })
        return result
    }
}
