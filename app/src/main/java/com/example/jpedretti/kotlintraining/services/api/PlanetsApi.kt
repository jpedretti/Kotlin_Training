package com.example.jpedretti.kotlintraining.services.api

import com.example.jpedretti.kotlintraining.services.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.services.responseModels.SwapiResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetsApi {

    @GET("planets")
    fun getPlanets(@Query("format") format: String?) : Call<SwapiResult<PlanetResult>>
}