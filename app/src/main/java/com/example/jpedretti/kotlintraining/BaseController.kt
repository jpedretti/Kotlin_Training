package com.example.jpedretti.kotlintraining

import android.util.Log
import java.util.*

open class BaseController(val context: String) {
    fun doSomeBaseControllerStuff() {
        val x = 1
        val timeInMillis = Calendar.getInstance().timeInMillis
        val y = x * timeInMillis
        Log.d("BaseControllerStuff", y.toString())
    }
}
