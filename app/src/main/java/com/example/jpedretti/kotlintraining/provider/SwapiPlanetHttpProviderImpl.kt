package com.example.jpedretti.kotlintraining.provider

import com.example.jpedretti.kotlintraining.infrastructure.CoroutineContextInjector
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import kotlinx.coroutines.async

class SwapiPlanetHttpProviderImpl : BaseSwapiApi(), SwapiPlanetProvider {

    private val planetService = swapi.create(PlanetsApi::class.java)

    override fun getPlanets(): SwapiResult<PlanetResult> {
        val planetsCall = planetService.getPlanets()
        return planetsCall.execute().body()!!
    }
}
