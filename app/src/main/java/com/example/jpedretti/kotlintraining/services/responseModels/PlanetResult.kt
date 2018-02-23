package com.example.jpedretti.kotlintraining.services.responseModels

import com.google.gson.annotations.SerializedName

class PlanetResult {
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("rotation_period")
    lateinit var rotationPeriod: String
    @SerializedName("orbital_period")
    lateinit var orbitalPeriod: String
    @SerializedName("diameter")
    lateinit var diameter: String
    @SerializedName("climate")
    lateinit var climate: String
    @SerializedName("gravity")
    lateinit var gravity: String
    @SerializedName("terrain")
    lateinit var terrain: String
    @SerializedName("surface_water")
    lateinit var surfaceWater: String
    @SerializedName("population")
    lateinit var population: String
}