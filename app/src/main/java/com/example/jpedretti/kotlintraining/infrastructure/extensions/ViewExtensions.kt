package com.example.jpedretti.kotlintraining.infrastructure.extensions

import android.view.View
import android.view.View.*

var View.visible: Boolean?
    get() = when (visibility) {
        VISIBLE -> true
        INVISIBLE -> false
        else -> null
    }
    set(value) {
        visibility = when (value) {
            true -> VISIBLE
            false -> INVISIBLE
            null -> GONE
        }
    }