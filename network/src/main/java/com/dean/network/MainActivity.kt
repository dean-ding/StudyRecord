package com.dean.network

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), NetworkObserver {

    private var receiver: NetworkReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerBroadcast()
    }

    private fun registerBroadcast() {
        receiver = NetworkReceiver()
        receiver?.register(this@MainActivity)

        thread {
            SystemClock.sleep(2000)
            receiver?.post(NetworkType.WIFI)
        }
    }

    override fun onConnect(networkType: NetworkType) {

    }

    override fun onDisConnect() {

    }

    @Network(networkType = NetworkType.WIFI)
    fun networkChanged(networkType: NetworkType) {
        Log.i(javaClass.name, "networkType is ${networkType}")
    }
}
