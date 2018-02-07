package com.example.jpedretti.kotlintraining.models

import android.databinding.ObservableField

data class MyModel(var name: ObservableField<String?>, var age: ObservableField<Int?>) {
    var loading: ObservableField<Boolean> = ObservableField(false)
}