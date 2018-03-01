package com.example.jpedretti.kotlintraining.business

import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import kotlinx.coroutines.experimental.Deferred

interface SwapiBusiness {
    fun getPlanetsAsync() : Deferred<SwapiResult<PlanetResult>?>
}