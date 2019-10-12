package com.example.jpedretti.kotlintraining.infrastructure.converters

import androidx.databinding.BindingAdapter
import android.view.View
import com.example.jpedretti.kotlintraining.infrastructure.extensions.visible

class BindingConverters {

    companion object {

        @JvmStatic
        @BindingAdapter("visible")
        fun View.setVisible(visible: Boolean) {
            this.visible = visible
        }
    }
}