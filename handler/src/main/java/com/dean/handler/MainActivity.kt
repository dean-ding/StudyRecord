package com.dean.handler

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()
    }

    private fun test() {
        thread {
            mHandler = object : Handler() {
                override fun handleMessage(message: Message) {
                    println("message what is ${message.what}")
                }
            }
            Looper.loop()
        }

        thread {
            SystemClock.sleep(3000)
            mHandler?.sendEmptyMessage(111)
            mHandler?.sendEmptyMessageDelay(112, 5000L)
            mHandler?.sendEmptyMessageDelay(113, 1000L)
        }

    }
}
