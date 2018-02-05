package com.example.jpedretti.kotlintraining

import android.databinding.ObservableField
import android.util.Log
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class MyController(context: String) : BaseController(context) {

    fun createMyModel(): MyModel {
        return MyModel(ObservableField(context), ObservableField(12))
    }

    fun lambdaWithListsAsync() = async(CommonPool) {
        delay(2000)
        val models = listOf(MyModel(ObservableField("banana"), ObservableField(5)),
                MyModel(ObservableField("pera"), ObservableField(12)),
                MyModel(ObservableField("maça"), ObservableField(3)),
                MyModel(ObservableField("kiwi"), ObservableField(18)))
        models.filter { it.name.get()!!.contains("a") }
                .sortedBy { it.age.get() }
                .forEach {  Log.d("banana", "${it.name.get()}: ${it.age.get()}")  }
        models.map { it.name.get()!!.toUpperCase() }
                .forEach {  Log.d("banana", it)  }
        //"done"
    }
}