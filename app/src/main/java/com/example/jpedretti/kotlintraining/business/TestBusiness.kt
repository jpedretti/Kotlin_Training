package com.example.jpedretti.kotlintraining.business

import kotlinx.coroutines.Deferred

interface TestBusiness {
    fun doServiceStuffAsync() : Deferred<String>
}