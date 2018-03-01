package com.example.jpedretti.kotlintraining.provider

interface ConnectivityProvider {
    fun isConnectedToInternet() : Boolean
}