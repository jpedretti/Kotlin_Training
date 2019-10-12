package com.example.jpedretti.kotlintraining.business

import com.example.jpedretti.kotlintraining.provider.ConnectivityProvider
import com.example.jpedretti.kotlintraining.provider.SwapiPlanetProvider
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class SwapiBusinessImpl(private val swapiHttpProvider: SwapiPlanetProvider,
                        private val swapiOfflineProvider: SwapiPlanetProvider,
                        private val connectivityProvider: ConnectivityProvider)
    : SwapiBusiness {

    override fun getPlanetsAsync(): Deferred<SwapiResult<PlanetResult>?> = CoroutineScope(IO).async {
        if (connectivityProvider.isConnectedToInternet())
            swapiHttpProvider.getPlanets()
        else
            swapiOfflineProvider.getPlanets()
    }
}