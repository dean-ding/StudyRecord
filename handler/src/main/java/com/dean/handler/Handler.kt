package com.dean.handler


/**
 * Created: tvt on 2019-08-23 14:43
 */
abstract class Handler {

    private var looper: Looper? = null
    private var queue: MessageQueue? = null

    init {
        Looper.prepare()
        this@Handler.looper = Looper.myLooper()
        queue = looper?.queue
    }

    fun obtainMessage(): Message {
        val message = Message()
        message.target = this
        return message
    }

    fun sendMessage(message: Message) {
        insertMessage(message, System.currentTimeMillis())
    }

    fun sendMessageDelay(message: Message, delay: Long) {
        insertMessage(message, delay + System.currentTimeMillis())
    }

    fun sendEmptyMessage(what: Int) {
        val message = obtainMessage()
        message.what = what
        insertMessage(message, System.currentTimeMillis())
    }

    fun sendEmptyMessageDelay(what: Int, delay: Long) {
        val message = obtainMessage()
        message.what = what
        insertMessage(message, delay + System.currentTimeMillis())
    }

    /**
     * 插入Message到queue中，通过时间进行排序
     */
    private fun insertMessage(message: Message, time: Long) {
        queue?.enqueueMessage(message, time)
    }

    fun dispatchMessage(message: Message) {
        handleMessage(message)
    }

    abstract fun handleMessage(message: Message)

}