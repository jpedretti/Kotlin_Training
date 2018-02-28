package com.example.jpedretti.kotlintraining.manager

import com.example.jpedretti.kotlintraining.infrastructure.CoroutineContextInjector
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class TestManagerImpl : TestManager {
    override fun doServiceStuffAsync() =
            async(CoroutineContextInjector.bgContext) {
        delay(1000)
        "May the force be with you!"
    }
}