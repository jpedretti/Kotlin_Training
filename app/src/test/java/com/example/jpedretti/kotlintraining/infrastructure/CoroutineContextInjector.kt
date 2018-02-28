package com.example.jpedretti.kotlintraining.infrastructure

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextInjector {
    companion object {
        val uiContext: CoroutineContext = Unconfined
        val bgContext: CoroutineContext = Unconfined
    }
}