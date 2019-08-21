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

    fun getNetWorkState(context: Context): NetworkType {
        //得到连接管理器对象
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetworkInfo = connectivityManager
            .activeNetworkInfo
        //如果网络连接，判断该网络类型
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            if (activeNetworkInfo.type === ConnectivityManager.TYPE_WIFI) {
                return NetworkType.WIFI//wifi
            } else if (activeNetworkInfo.type === ConnectivityManager.TYPE_MOBILE) {
                return NetworkType.CMWAP//mobile
            }
        }
        return NetworkType.NONE
    }
}