package com.example.jpedretti.kotlintraining.provider

import android.net.ConnectivityManager

class ConnectivityProviderImpl(private val connectivityManager : ConnectivityManager) : ConnectivityProvider {

    override fun isConnectedToInternet(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}