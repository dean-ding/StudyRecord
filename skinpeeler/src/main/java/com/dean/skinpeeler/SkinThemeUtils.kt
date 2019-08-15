package com.dean.skinpeeler

/**
 * Created: tvt on 2019-08-14 10:30
 */
class SkinThemeUtils {
    companion object {
        val instance: SkinThemeUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SkinThemeUtils()
        }
    }
}