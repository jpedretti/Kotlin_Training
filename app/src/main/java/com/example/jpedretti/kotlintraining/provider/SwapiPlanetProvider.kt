package com.example.jpedretti.kotlintraining.provider

import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import kotlinx.coroutines.experimental.Deferred

interface SwapiPlanetProvider {

    fun getPlanetsAsync() : Deferred<SwapiResult<PlanetResult>?>
}