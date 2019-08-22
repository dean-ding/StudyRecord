package com.dean.image.compress

import android.graphics.Bitmap

/**
 * Created: tvt on 2019-08-21 16:22
 */
class BitmapUtils {

    companion object {
        val instance: BitmapUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            BitmapUtils()
        }
    }

    fun compressSize(width: Int, height: Int, fileName: String): Bitmap {


    }

    fun compressSize(width: Int, height: Int, bitmap: Bitmap): Bitmap {

    }


}