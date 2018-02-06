package com.example.jpedretti.kotlintraining

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class TestServiceImpl : TestService {
    override fun doServiceStuffAsync() = async {
        delay(1000)
        "finished doing service stuff"
    }
}