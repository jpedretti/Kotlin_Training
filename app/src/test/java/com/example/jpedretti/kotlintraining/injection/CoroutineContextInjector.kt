package com.example.jpedretti.kotlintraining.injection

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextInjector {
    companion object {
        val uiContext: CoroutineContext = Unconfined
        val bgContext: CoroutineContext = Unconfined
    }
}