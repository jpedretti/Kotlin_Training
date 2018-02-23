package com.example.jpedretti.kotlintraining.services.responseModels

import com.google.gson.annotations.SerializedName

class SwapiResult<T> {

    @SerializedName("count")
    var count: Int = 0
    @SerializedName("next")
    var nextPage: String? = null
    @SerializedName("previous")
    var previousPage: String? = null
    @SerializedName("results")
    lateinit var results: List<T>
}