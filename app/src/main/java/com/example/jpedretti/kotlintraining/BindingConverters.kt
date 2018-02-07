package com.example.jpedretti.kotlintraining

import android.view.View.*

class BindingConverters {
    companion object {

        @JvmStatic
        fun boolToVisibility(boolean: Boolean?) =
                if (boolean == null) GONE else boolToVisibility(boolean)

        @JvmStatic
        fun boolToVisibilityNegated(boolean: Boolean?) = boolToVisibility(boolean?.not())

        @JvmStatic
        private fun boolToVisibility(boolean: Boolean) = if (boolean) VISIBLE else INVISIBLE
    }
}