package com.example.jpedretti.kotlintraining.models

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.example.jpedretti.kotlintraining.services.responseModels.PlanetResult

class DiModel {
    var appName: ObservableField<String> = ObservableField()
    var testServiceDoStuffResult: ObservableField<String> = ObservableField()
    var loading: ObservableField<Boolean> = ObservableField(false)
    var message: ObservableField<String?> = ObservableField()
    var planets: ObservableList<PlanetResult> = ObservableArrayList()
}