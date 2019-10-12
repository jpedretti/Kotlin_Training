package com.example.jpedretti.kotlintraining.provider

import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult
import com.example.jpedretti.kotlintraining.provider.responseModels.SwapiResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetsApi {

    @GET("planets")
    fun getPlanets(@Query("format") format: String? = null) : Call<SwapiResult<PlanetResult>>
}