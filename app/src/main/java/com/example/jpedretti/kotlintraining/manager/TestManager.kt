package com.example.jpedretti.kotlintraining.manager

import kotlinx.coroutines.experimental.Deferred

interface TestManager {
    fun doServiceStuffAsync() : Deferred<String>
}