package com.dean.network

import android.app.Application
import android.util.Log

/**
 * Created: tvt on 2019-08-15 20:14
 */
class NetworkManager {

    companion object {
        val instance: NetworkManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkManager()
        }
    }

    constructor() {
        receiver = NetworkReceiver()
    }

    private var application: Application? = null
    private var receiver: NetworkReceiver? = null

    fun init(application: Application) {
        this.application = application
    }

    fun setCallback(observer: NetworkObserver) {
        this.receiver?.setCallback(observer)
    }

    fun getApplication(): Application? {
        if (this.application == null) {
            Log.e(javaClass.name, "application is null!")
        }
        return this.application
    }
}