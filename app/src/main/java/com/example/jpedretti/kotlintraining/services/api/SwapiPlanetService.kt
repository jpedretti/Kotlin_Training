package com.example.jpedretti.kotlintraining.services.api

import com.example.jpedretti.kotlintraining.services.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.services.responseModels.SwapiResult
import kotlinx.coroutines.experimental.Deferred

interface SwapiPlanetService {

    fun getPlanetsAsync() : Deferred<SwapiResult<PlanetResult>?>
}