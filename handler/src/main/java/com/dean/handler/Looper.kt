package com.dean.handler

import android.os.SystemClock

class Looper {

    var queue: MessageQueue = MessageQueue()

    init {
    }


    companion object {
        private var threadLocal: ThreadLocal<Looper>? = null

        fun myLooper(): Looper? {
            return threadLocal?.get()
        }

        fun prepare() {
            if (threadLocal != null) {
                threadLocal?.remove()
                threadLocal = null
            }
            threadLocal = ThreadLocal()
            threadLocal?.set(Looper())
        }

        fun loop() {
            while (true) {
                val looper = myLooper()
                looper?.let {
                    val messageQueue = looper.queue
                    val message = messageQueue.next()
                    message?.let { msg ->
                        if (msg.time <= System.currentTimeMillis()) {
                            msg.target?.dispatchMessage(msg)
                            messageQueue.remove()
                        }
                    }
                }
                SystemClock.sleep(1)
            }
        }
    }
}