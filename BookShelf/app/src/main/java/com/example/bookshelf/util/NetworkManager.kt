package com.example.bookshelf.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.getSystemService

class NetworkManager {

    fun Chechconnectivity(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activNetwork?.isConnected != null){
            return activNetwork.isConnected

        }else{

            return false
        }
    }


}