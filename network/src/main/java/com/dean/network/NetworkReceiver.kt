package com.dean.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created: tvt on 2019-08-15 19:53
 */
class NetworkReceiver() : BroadcastReceiver() {

    private var networkType: NetworkType? = null
    private var mObserver: NetworkObserver? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        // 接收到网络变化通知
        intent?.let {
            if (NetworkConstants.ANDROID_CONN_CHANGE_ACTION == it.action) {

                Log.i(javaClass.name, "network is changed!!!")

                mObserver?.onConnect()
            }
        }
    }

    fun setCallback(observer: NetworkObserver) {
        this@NetworkReceiver.mObserver = observer
    }

}