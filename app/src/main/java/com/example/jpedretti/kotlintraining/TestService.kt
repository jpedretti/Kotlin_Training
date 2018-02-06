package com.example.jpedretti.kotlintraining

import kotlinx.coroutines.experimental.Deferred

interface TestService {
    fun doServiceStuffAsync() : Deferred<String>
}