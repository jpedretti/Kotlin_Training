package com.example.jpedretti.kotlintraining

import android.databinding.BindingAdapter
import android.view.View
import com.example.jpedretti.kotlintraining.extensions.visible

class BindingConverters {

    companion object {

        @JvmStatic
        @BindingAdapter("visible")
        fun View.setVisible(visible: Boolean) {
            this.visible = visible
        }
    }
}