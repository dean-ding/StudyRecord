package com.dean.handler

import android.os.Looper

/**
 * Created: tvt on 2019-08-23 14:43
 */
class Handler {

    private lateinit var looper: Looper
    private var queue: Message? = null

    constructor() {
        looper = Looper.myLooper()
    }

    constructor(looper: Looper) {
        this@Handler.looper = looper
    }

    fun obtainMessage(): Message {
        return Message()
    }

    fun sendMessage(message: Message) {
        insertMessage(message)
    }

    fun sendMessageDelay(message: Message, delay: Long) {

    }

    fun sendEmptyMessage(what: Int) {
        val message = Message()
        insertMessage(message)
    }

    fun sendEmptyMessageDelay(what: Int, delay: Long) {

    }

    /**
     * 插入Message到queue中，通过时间进行排序
     */
    private fun insertMessage(message: Message) {
        if (queue == null) {
            queue = message
            return
        }

    }

}