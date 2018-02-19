package com.example.jpedretti.kotlintraining.services

import android.support.annotation.StringRes

interface ResourcesService {
    fun getString(@StringRes id: Int) : String
}