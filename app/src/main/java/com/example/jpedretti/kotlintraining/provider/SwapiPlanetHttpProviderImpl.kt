package com.example.jpedretti.kotlintraining.provider

import com.example.jpedretti.kotlintraining.infrastructure.CoroutineContextInjector
import kotlinx.coroutines.experimental.async

class SwapiPlanetHttpProviderImpl : BaseSwapiApi(), SwapiPlanetProvider {

    private val planetService = swapi.create(PlanetsApi::class.java)

    override fun getPlanetsAsync() = async (CoroutineContextInjector.bgContext) {
        val planetsCall = planetService.getPlanets(null)
        planetsCall.execute().body()
    }
}
