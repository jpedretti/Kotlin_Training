package com.example.jpedretti.kotlintraining.business

import com.example.jpedretti.kotlintraining.infrastructure.CoroutineContextInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class TestBusinessImpl : TestBusiness {
    override fun doServiceStuffAsync() =
           CoroutineScope(IO).async(CoroutineContextInjector.bgContext) {
        delay(1000)
        "May the force be with you!"
    }
}