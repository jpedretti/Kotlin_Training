package com.example.jpedretti.kotlintraining.services.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseSwapiApi {
    protected val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}