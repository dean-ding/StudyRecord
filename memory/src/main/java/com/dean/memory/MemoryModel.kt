package com.dean.memory

import android.os.SystemClock
import kotlin.concurrent.thread

/**
 * Created: tvt on 2019-08-15 15:12
 */
class MemoryModel {

    fun login(name: String, password: String, callback: IModelCallback) {
        thread {
            SystemClock.sleep(10000)
            if (name == "admin" && password == "123456") {
                callback.login(0)
            } else {
                callback.login(-1)
            }
        }
    }

}