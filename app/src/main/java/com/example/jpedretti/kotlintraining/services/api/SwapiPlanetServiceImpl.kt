package com.example.jpedretti.kotlintraining.services.api

import com.example.jpedretti.kotlintraining.injection.CoroutineContextInjector
import kotlinx.coroutines.experimental.async

class SwapiPlanetServiceImpl : BaseSwapiApi(), SwapiPlanetService {

    private val planetService = retrofit.create(PlanetsApi::class.java)

    override fun getPlanetsAsync() = async (CoroutineContextInjector.bgContext) {
        val planetsCall = planetService.getPlanets(null)
        planetsCall.execute().body()
    }
}
