package com.example.jpedretti.kotlintraining.services

import android.content.Context
import android.support.annotation.StringRes


class ResourcesServiceImpl(private val context: Context) : ResourcesService {

    override fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }
}