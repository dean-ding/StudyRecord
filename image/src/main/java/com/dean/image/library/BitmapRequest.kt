package com.dean.image.library

import android.content.Context
import android.widget.ImageView
import java.lang.ref.SoftReference

/**
 * Created: tvt on 2019-08-20 19:43
 */
class BitmapRequest {

    var url: String = ""

    var context: Context

    var imageView: SoftReference<ImageView>? = null

    var resId: Int = 0

    var urlMd5: String = ""

    var requestListener: RequestListener? = null

    constructor(context: Context) {
        this@BitmapRequest.context = context
    }

    fun load(url: String): BitmapRequest {
        this@BitmapRequest.url = url
        this@BitmapRequest.urlMd5 = EncryptUtils.encryptMD5ToString(url)
        return this
    }

    fun listener(listener: RequestListener): BitmapRequest {
        this@BitmapRequest.requestListener = listener
        return this
    }

    fun loading(resId: Int): BitmapRequest {
        this@BitmapRequest.resId = resId
        return this
    }

    fun into(imageView: ImageView): BitmapRequest {
        this@BitmapRequest.imageView = SoftReference(imageView)
        return this
    }

    fun getImageView(): ImageView? {
        return imageView?.get()
    }
}