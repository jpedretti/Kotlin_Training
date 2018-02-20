package com.example.jpedretti.kotlintraining.controllers

import android.databinding.ObservableField
import android.util.Log
import com.example.jpedretti.kotlintraining.injection.CoroutineContextInjector
import com.example.jpedretti.kotlintraining.models.MyModel
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class MyController(textToDoStuffWith: String) : BaseController(textToDoStuffWith) {

    fun createOtherModel(): OtherModel {
        return OtherModel("yoda", 900)
    }

    fun lambdaWithListsAsync() = async(CoroutineContextInjector.bgContext) {
        delay(2000)
        val models = listOf(MyModel(ObservableField("MyController"), ObservableField(5)),
                MyModel(ObservableField("pera"), ObservableField(12)),
                MyModel(ObservableField("maça"), ObservableField(3)),
                MyModel(ObservableField("kiwi"), ObservableField(18)))
        models.filter { it.name.get()!!.contains("a") }
                .sortedBy { it.age.get() }
                .forEach { Log.d("MyController", "${it.name.get()}: ${it.age.get()}") }
        models.map { it.name.get()!!.toUpperCase() }
                .forEach { Log.d("MyController", it) }
        //"done"
    }
}

data class OtherModel(var name: String, var age: Int)