package com.example.jpedretti.kotlintraining

import android.app.Application
import com.example.jpedretti.kotlintraining.services.NotificationService
import com.example.jpedretti.kotlintraining.services.ResourcesService
import com.example.jpedretti.kotlintraining.services.TestService
import com.example.jpedretti.kotlintraining.viewModels.DIViewModel
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiViewModelTest {

    @Mock
    lateinit var application: Application
    @Mock
    lateinit var testService : TestService
    @Mock
    lateinit var resourcesService : ResourcesService
    @Mock
    lateinit var notificationService : NotificationService

    @Test
    fun dIViewModel_SuccessfullyInit() {
        org.mockito.Mockito.`when`(resourcesService.getString(R.string.app_name))
                .thenReturn("KotlinTraining")

        val viewModel = DIViewModel(testService, resourcesService, notificationService)
        viewModel.onCreate()

        assertEquals("KotlinTraining", viewModel.model.appName.get())
    }

    @Test
    fun dIViewModel_SuccessfullyDoServiceStuff() {
        `when`(testService.doServiceStuffAsync())
                .thenReturn(CompletableDeferred("finished doing service stuff"))

        val viewModel = DIViewModel(testService, resourcesService, notificationService)
        viewModel.onCreate()
        runBlocking {
            viewModel.doServiceStuffByViewModelClick()
        }

        assertEquals("finished doing service stuff",
                viewModel.model.testServiceDoStuffResult.get())
    }
}
