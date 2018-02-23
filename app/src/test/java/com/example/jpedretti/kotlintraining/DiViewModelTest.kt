package com.example.jpedretti.kotlintraining

import android.app.Application
import com.example.jpedretti.kotlintraining.services.NotificationService
import com.example.jpedretti.kotlintraining.services.ResourcesService
import com.example.jpedretti.kotlintraining.services.api.SwapiPlanetService
import com.example.jpedretti.kotlintraining.services.TestService
import com.example.jpedretti.kotlintraining.services.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.services.responseModels.SwapiResult
import com.example.jpedretti.kotlintraining.viewModels.DIViewModel
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
    lateinit var application: Application
    @Mock
    private lateinit var testService : TestService
    @Mock
    private lateinit var resourcesService : ResourcesService
    @Mock
    private lateinit var notificationService : NotificationService
    @Mock
    private lateinit var swapiPlanetService: SwapiPlanetService
    private lateinit var viewModel: DIViewModel

    @Before
    fun setup() {
        viewModel = DIViewModel(testService, resourcesService, notificationService, swapiPlanetService)
    }

    @Test
    fun dIViewModel_SuccessfullyInit() {
        org.mockito.Mockito.`when`(resourcesService.getString(R.string.app_name))
                .thenReturn("KotlinTraining")

        viewModel.onCreate()

        assertEquals("KotlinTraining", viewModel.model.appName.get())
        verify(notificationService, times(1)).createChannel()
        verify(testService, times(0)).doServiceStuffAsync()
        verify(notificationService, times(0))
                .createNotificationAndNotify(anyInt(), anyString(),anyString(),anyInt())
    }

    @Test
    fun dIViewModel_SuccessfullyDoServiceStuff() {
        `when`(testService.doServiceStuffAsync())
                .thenReturn(CompletableDeferred("May the force be with you!"))

        viewModel.onCreate()
        runBlocking {
            viewModel.doServiceStuffByViewModelClick()
        }

        verify(notificationService,times(1))
                .createNotificationAndNotify(anyInt(), anyString(),anyString(),anyInt())
        verify(testService, times(1)).doServiceStuffAsync()
        assertEquals("May the force be with you!",
                viewModel.model.testServiceDoStuffResult.get())
    }

    @Test
    fun diViewModel_SuccessfullyGetPlanets() {
        val result = getPlanetsResult()

        `when`(swapiPlanetService.getPlanetsAsync())
                .thenReturn(CompletableDeferred(result))

        viewModel.onCreate()
        runBlocking {
            viewModel.getPlanetsClicked()
        }

        verify(testService, times(0)).doServiceStuffAsync()
        verify(notificationService, times(0))
                .createNotificationAndNotify(anyInt(), anyString(),anyString(),anyInt())
        verify(swapiPlanetService, times(1))
                .getPlanetsAsync()
        assertEquals(2, viewModel.model.planets.size)

        assertEquals("Bananinha", viewModel.model.planets[0].name)
        assertEquals("1", viewModel.model.planets[0].orbitalPeriod)
        assertEquals("2", viewModel.model.planets[0].rotationPeriod)

        assertEquals("Pirulito", viewModel.model.planets[1].name)
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
            this.name = "Bananinha"
            this.orbitalPeriod = "1"
            this.rotationPeriod = "2"
        })

        (result.results as ArrayList).add(PlanetResult().apply {
            this.name = "Pirulito"
            this.orbitalPeriod = "4"
            this.rotationPeriod = "5"
        })
        return result
    }
}
