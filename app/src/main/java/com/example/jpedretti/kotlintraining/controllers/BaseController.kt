package com.example.jpedretti.kotlintraining.controllers

open class BaseController(private val textToDoStuffWith: String) {
    fun doSomeBaseControllerStuff() : String {
        return "BaseControllerStuff: stuff done: $textToDoStuffWith"
    }
}
