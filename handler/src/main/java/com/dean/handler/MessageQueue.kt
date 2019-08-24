package com.dean.handler

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

/**
 * Created: tvt on 2019-08-23 14:43
 */
class MessageQueue {

    private var mMessage: Message? = null
    private var mQueue: BlockingQueue<Message>? = null

    constructor() {
        mQueue = ArrayBlockingQueue(50)
    }

    fun enqueueMessage(msg: Message, time: Long) {

        var p = mMessage
        msg.time = time

        if (p == null || p.time > msg.time) {
            msg.next = p
            mMessage = msg
            return
        }

        var prev: Message? = null
        while (true) {
            prev = p
            p = p?.next

            if (p == null || p.time > msg.time) {
                break
            }
        }
        prev?.next = msg
        msg.next = p
    }

    fun next(): Message? {
        return mMessage
    }

    fun remove() {
        mMessage = mMessage?.next
    }
}