package com.dean.network

import java.lang.reflect.Method

/**
 * Created: tvt on 2019-08-15 20:14
 */
class NetworkManager(type: Class<*>, networkType: NetworkType, method: Method) {

    var type: Class<*>

    var networkType: NetworkType = NetworkType.AUTO

    var method: Method

    init {
        this@NetworkManager.type = type
        this@NetworkManager.networkType = networkType
        this@NetworkManager.method = method
    }
}