package com.dean.network

/**
 * Created: tvt on 2019-08-15 20:12
 */
interface NetworkObserver {

    fun onConnect(networkType: NetworkType)

    fun onDisConnect()
}