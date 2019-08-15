package com.dean.network

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), NetworkObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerBroadcast()
    }

    private fun registerBroadcast() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(NetworkConstants.ANDROID_CONN_CHANGE_ACTION)
        this@MainActivity.registerReceiver(NetworkReceiver(this@MainActivity), intentFilter)
    }

    override fun onConnect(networkType: NetworkType) {

    }

    override fun onDisConnect() {

    }
}
