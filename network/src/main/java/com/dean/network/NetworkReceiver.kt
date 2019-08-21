package com.dean.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.lang.reflect.Modifier

/**
 * Created: tvt on 2019-08-15 19:53
 */
class NetworkReceiver() : BroadcastReceiver() {

    private var networkType: NetworkType? = null
    private var mObserver: NetworkObserver? = null
    private val map = mutableMapOf<Any, MutableList<NetworkManager>>()

    override fun onReceive(context: Context?, intent: Intent?) {
        // 接收到网络变化通知
        intent?.let {
            if (NetworkConstants.ANDROID_CONN_CHANGE_ACTION == it.action) {
                context?.let {
                    networkType = NetworkUtils.instance.getNetWorkState(context)
                    networkType?.let { type ->
                        post(type)
                    }
                }
            }
        }
    }

    fun post(networkType: NetworkType) {
        val keys = map.keys
        for (key in keys) {
            val managers = map[key]
            managers?.let { managers ->
                for (manager in managers) {
                    if (manager.type.isAssignableFrom(networkType.javaClass)) {
                        when (manager.networkType) {
                            NetworkType.AUTO -> {

                            }
                            NetworkType.WIFI -> {

                            }
                            NetworkType.NONE -> {

                            }
                            NetworkType.CMWAP -> {

                            }
                        }
                        invoke(key, manager, networkType)
                    }
                }
            }
        }
    }

    private fun invoke(clazz: Any, manager: NetworkManager, networkType: NetworkType) {
        manager.method.invoke(clazz, networkType)
    }

    fun register(obj: Any) {
        map[obj] = findAnnotations(obj)
    }

    private fun findAnnotations(obj: Any): MutableList<NetworkManager> {
        val manager = mutableListOf<NetworkManager>()
        val declaredMethods = obj.javaClass.declaredMethods
        for (method in declaredMethods) {
            if (method.isAnnotationPresent(Network::class.java)) {
                val modifiers = method.modifiers
                if (modifiers and Modifier.PUBLIC != 0) {
                    val network = method.getAnnotation(Network::class.java)
                    network?.let {
                        val parameterTypes = method.parameterTypes
                        if (parameterTypes.size == 1) {//只接收一个参数
                            manager.add(NetworkManager(parameterTypes[0], it.networkType, method))
                        }
                    }
                } else {
                    Log.e(javaClass.name, "method must be public")
                }
            }
        }
        return manager
    }

}