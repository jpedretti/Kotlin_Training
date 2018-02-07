package com.example.jpedretti.kotlintraining.services

import kotlinx.coroutines.experimental.Deferred

interface TestService {
    fun doServiceStuffAsync() : Deferred<String>
}