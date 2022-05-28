package com.c.mvvmkotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast

class NetworkUtils {

    companion object {

        fun isInternetAvailable(context: Context): Boolean {
            val cmg = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Android 10+
                cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                }
            } else {
                return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
            }

            return false
        }
    }
}