package com.dean.handler

/**
 * Created: tvt on 2019-08-23 14:45
 */
class Message {
    var time: Long = System.currentTimeMillis()

    var obj: Any? = null

    var what: Int = 0

    var next: Message? = null

    var target: Handler? = null
}