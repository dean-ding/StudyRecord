package com.dean.handler

import com.dean.handler.Message

/**
 * Created: tvt on 2019-08-23 14:43
 */
class MessageQueue {

    private var mMessage: Message? = null

    fun enqueueMessage(msg: Message, time: Long) {

        var p = mMessage

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
}