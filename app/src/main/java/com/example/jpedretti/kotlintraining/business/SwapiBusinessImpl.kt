package com.example.jpedretti.kotlintraining.business

import com.example.jpedretti.kotlintraining.provider.ConnectivityProvider
import com.example.jpedretti.kotlintraining.provider.SwapiPlanetProvider
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import kotlinx.coroutines.experimental.Deferred

class SwapiBusinessImpl(private val swapiHttpProvider: SwapiPlanetProvider,
                        private val swapiOfflineProvider: SwapiPlanetProvider,
                        private val connectivityProvider: ConnectivityProvider)
    : SwapiBusiness {

    override fun getPlanetsAsync() : Deferred<SwapiResult<PlanetResult>?> {
        return if (connectivityProvider.isConnectedToInternet())
            swapiHttpProvider.getPlanetsAsync()
        else
            swapiOfflineProvider.getPlanetsAsync()
    }
}