package com.example.jpedretti.kotlintraining.infrastructure

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext


class CoroutineContextInjector {
    companion object {
        val uiContext: CoroutineContext = Main
        val bgContext: CoroutineContext = IO
    }
}