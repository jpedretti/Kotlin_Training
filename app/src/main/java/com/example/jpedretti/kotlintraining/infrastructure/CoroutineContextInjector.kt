package com.example.jpedretti.kotlintraining.infrastructure

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextInjector {
    companion object {
        val uiContext: CoroutineContext = UI
        val bgContext: CoroutineContext = CommonPool
    }
}