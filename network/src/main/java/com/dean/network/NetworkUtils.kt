package com.dean.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created: tvt on 2019-08-15 19:45
 */
class NetworkUtils {

    companion object {
        val instance: NetworkUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkUtils()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetworkAvaliable(context: Context) {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetworkInfo = connectivityManager.allNetworks
        activeNetworkInfo?.let {
            for (network in it) {

            }
        }
    }
}