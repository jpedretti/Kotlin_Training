package com.example.jpedretti.kotlintraining.business

import kotlinx.coroutines.experimental.Deferred

interface TestBusiness {
    fun doServiceStuffAsync() : Deferred<String>
}