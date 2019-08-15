package com.dean.network

import android.app.Application

/**
 * Created: tvt on 2019-08-15 20:28
 */
class NetworkApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        NetworkManager.instance.init(this)
    }
}