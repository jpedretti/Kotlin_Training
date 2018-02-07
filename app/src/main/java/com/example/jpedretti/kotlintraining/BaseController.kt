package com.example.jpedretti.kotlintraining

open class BaseController(private val textToDoStuffWith: String) {
    fun doSomeBaseControllerStuff() : String {
        return "BaseControllerStuff: stuff done: $textToDoStuffWith"
    }
}
