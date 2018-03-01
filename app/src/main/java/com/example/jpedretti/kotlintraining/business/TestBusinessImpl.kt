package com.example.jpedretti.kotlintraining.business

import com.example.jpedretti.kotlintraining.infrastructure.CoroutineContextInjector
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class TestBusinessImpl : TestBusiness {
    override fun doServiceStuffAsync() =
            async(CoroutineContextInjector.bgContext) {
        delay(1000)
        "May the force be with you!"
    }
}