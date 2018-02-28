package com.example.jpedretti.kotlintraining.manager

import com.example.jpedretti.kotlintraining.provider.SwapiPlanetProvider
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import kotlinx.coroutines.experimental.Deferred

class SwapiManagerImpl(private val swapiHttpProvider: SwapiPlanetProvider,
                       private val swapiOfflineProvider: SwapiPlanetProvider,
                       private val customConnectivityManager: CustomConnectivityManager)
    : SwapiManager {

    override fun getPlanetsAsync() : Deferred<SwapiResult<PlanetResult>?> {
        return if (customConnectivityManager.isConnectedToInternet())
            swapiHttpProvider.getPlanetsAsync()
        else
            swapiOfflineProvider.getPlanetsAsync()
    }
}