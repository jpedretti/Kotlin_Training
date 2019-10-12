package com.example.jpedretti.kotlintraining.models

import androidx.lifecycle.MutableLiveData
import com.example.jpedretti.kotlintraining.provider.responseModels.PlanetResult

class DiModel {
    var appName: MutableLiveData<String> = MutableLiveData()
    var testServiceDoStuffResult: MutableLiveData<String> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false}
    var message: MutableLiveData<String?> = MutableLiveData()
    var planets: MutableLiveData<List<PlanetResult>> = MutableLiveData()
}