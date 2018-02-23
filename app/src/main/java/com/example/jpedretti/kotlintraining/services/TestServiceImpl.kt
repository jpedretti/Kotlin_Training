package com.example.jpedretti.kotlintraining.services

import com.example.jpedretti.kotlintraining.injection.CoroutineContextInjector
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class TestServiceImpl : TestService {
    override fun doServiceStuffAsync() =
            async(CoroutineContextInjector.bgContext) {
        delay(1000)
        "May the force be with you!"
    }
}