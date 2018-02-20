package com.example.jpedretti.kotlintraining

import com.example.jpedretti.kotlintraining.services.NotificationService
import com.example.jpedretti.kotlintraining.services.ResourcesService
import com.example.jpedretti.kotlintraining.services.TestService
import com.example.jpedretti.kotlintraining.viewModels.DIViewModel
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var viewModel: DIViewModel
    private lateinit var testService: TestService
    private lateinit var resourcesService: ResourcesService
    private lateinit var notificationService: NotificationService

    @Test
    fun addition_isCorrect() {
        4 shouldBe 2 + 2
    }

    @Test
    fun `Async test should pass`() {
        whenever(testService.doServiceStuffAsync()).doReturn(CompletableDeferred("test"))

        runBlocking {
            viewModel.doServiceStuffByViewModelClick()
//            delay(100)
            viewModel.model.testServiceDoStuffResult.get() shouldBe "test"
        }

    }

    @Before
    fun setup() {
        testService = mock()

        resourcesService = mock()
        notificationService = mock()

        viewModel = DIViewModel(testService, resourcesService, notificationService)
    }
}
