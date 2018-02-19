package com.example.jpedretti.kotlintraining.services

import android.app.Application
import android.support.annotation.StringRes


class ResourcesServiceImpl(private val app : Application) : ResourcesService {

    override fun getString(@StringRes id: Int): String {
        return app.getString(id)
    }
}