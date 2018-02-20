package com.example.jpedretti.kotlintraining

import android.app.Application
import com.example.jpedretti.kotlintraining.services.NotificationService
import com.example.jpedretti.kotlintraining.services.ResourcesService
import com.example.jpedretti.kotlintraining.services.TestService
import com.example.jpedretti.kotlintraining.viewModels.DIViewModel
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiViewModelTest : KoinTest {

    @Mock
    lateinit var application: Application

    @Before
    fun before(){
        startKoin(listOf(myModule))
    }

    @After
    fun after(){
        closeKoin()
    }

    @Test
    fun dIViewModel_SuccessfullyInit() {
        val testService : TestService by inject()
        val resourcesService : ResourcesService by inject()
        val notificationService : NotificationService by inject()
        org.mockito.Mockito.`when`(resourcesService.getString(R.string.app_name))
                .thenReturn("KotlinTraining")

        val viewModel = DIViewModel(testService, resourcesService, notificationService)
        viewModel.onCreate()

        assertEquals("KotlinTraining", viewModel.model.appName.get())
    }

    @Test
    fun dIViewModel_SuccessfullyDoServiceStuff() {
        val testService : TestService by inject()
        val resourcesService : ResourcesService by inject()
        val notificationService : NotificationService by inject()
        `when`(testService.doServiceStuffAsync())
                .thenReturn(async { "finished doing service stuff" })

        val viewModel = DIViewModel(testService, resourcesService, notificationService)
        viewModel.onCreate()
        runBlocking {
            viewModel.doServiceStuffByViewModelClick()
        }

        assertEquals("finished doing service stuff",
                viewModel.model.testServiceDoStuffResult.get())
    }

    private val myModule : Module = applicationContext {
        provide { Mockito.mock(TestService::class.java) as TestService }
        provide { Mockito.mock(ResourcesService::class.java) as ResourcesService }
        provide { Mockito.mock(NotificationService::class.java,
                Mockito.withSettings().stubOnly()) as NotificationService }
    }
}
