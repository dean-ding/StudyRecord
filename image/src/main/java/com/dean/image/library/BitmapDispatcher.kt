package com.dean.image.library

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created: tvt on 2019-08-20 19:52
 */
class BitmapDispatcher(queue: LinkedBlockingQueue<BitmapRequest>) : Thread() {

    // 只能从头部获取数据，添加时添加到最后。保证先进先出，并发下的线程安全
    private var requestQueue: LinkedBlockingQueue<BitmapRequest>? = null
    private var handler: Handler

    init {
        this@BitmapDispatcher.requestQueue = queue
        handler = Handler(Looper.getMainLooper())
    }

    override fun run() {
        super.run()

        while (!isInterrupted) {
            val take = requestQueue?.take()
            take?.let { request ->
                // 加载占位图
                showLoadingImage(request)
                // 下载图片
                val bitmap = findBitmap(request.url)
                // 加载图片
                loadImageView(request, bitmap)
            }
        }
    }

    private fun showLoadingImage(request: BitmapRequest) {
        if (request.resId == 0 || request.getImageView() == null) {
            return
        }
        val imageView = request.getImageView()
        val resId = request.resId
        handler.post {
            imageView?.setImageResource(resId)
        }
    }

    private fun findBitmap(url: String?): Bitmap? {
        var bitmap: Bitmap? = null
        bitmap = downloadBitmap(url)
        return bitmap
    }

    private fun downloadBitmap(uri: String?): Bitmap? {
        if (uri == null) {
            return null
        }
        if (TextUtils.isEmpty(uri)) {
            return null
        }
        val url = URL(uri)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        val inputStream = connection.inputStream
        val bitmap = BitmapFactory.decodeStream(inputStream)
        return bitmap
    }

    private fun loadImageView(request: BitmapRequest, bitmap: Bitmap?) {
        if (request.getImageView() == null || bitmap == null) {
            return
        }
        if (request.urlMd5 == request.getImageView()?.tag) {
            handler.post {
                request.getImageView()?.setImageBitmap(bitmap)
            }
        }
    }

}