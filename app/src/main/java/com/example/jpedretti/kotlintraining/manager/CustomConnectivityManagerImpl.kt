package com.example.jpedretti.kotlintraining.manager

import android.net.ConnectivityManager

class CustomConnectivityManagerImpl(private val connectivityManager : ConnectivityManager) : CustomConnectivityManager {

    override fun isConnectedToInternet(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}