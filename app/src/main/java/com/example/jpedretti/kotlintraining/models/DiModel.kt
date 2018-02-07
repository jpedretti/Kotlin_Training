package com.example.jpedretti.kotlintraining.models

import android.databinding.ObservableField

class DiModel() {
    var appName: ObservableField<String> = ObservableField()
    var testServiceDoStuffResult: ObservableField<String> = ObservableField()
    var loading: ObservableField<Boolean> = ObservableField(false)
    var message: ObservableField<String?> = ObservableField()
}