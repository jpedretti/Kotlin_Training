package com.example.jpedretti.kotlintraining.manager

import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import kotlinx.coroutines.experimental.Deferred

interface SwapiManager {
    fun getPlanetsAsync() : Deferred<SwapiResult<PlanetResult>?>
}