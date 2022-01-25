package com.example.studentmanagement.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.NetworkRequest

class ConnectionManager {

    fun checkConnectivity(context: Context): Boolean {

        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo

        if (activeNetwork?.isConnected != null) {

            return activeNetwork.isConnected
        } else {
            return false
        }

    }

}