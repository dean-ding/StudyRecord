package com.dean.skinpeeler

import android.util.AttributeSet
import android.view.View

/**
 * Created: tvt on 2019-08-14 09:25
 */
class SkinAttribute {

    private val mAttributes = mutableListOf<String>()
    private val mSkinViews = mutableListOf<SkinView>()

    init {
        mAttributes.add("background")
        mAttributes.add("src")
        mAttributes.add("textColor")
    }

    fun load(view: View, attrs: AttributeSet) {
        val attributeCount = attrs.attributeCount
        val skinPairArray = mutableListOf<SkinPair>()
        for (i in 0 until attributeCount) {
            val attributeName = attrs.getAttributeName(i)
            var resId = 0
            if (mAttributes.contains(attributeName)) {
                val attributeValue = attrs.getAttributeValue(i)
                // #为写死的颜色值
                if (attributeValue.startsWith("#")) {
                    continue
                }
                // ?为API中的主题颜色值
                if (attributeValue.startsWith("?")) {
                    // val attrId = Integer.parseInt(attributeValue.substring(1))
                } else {
                    resId = Integer.parseInt(attributeValue.substring(1))
                }
                if (resId != 0) {
                    val skinPair = SkinPair(attributeName, resId)
                    skinPairArray.add(skinPair)
                }
            }
            val skinView = SkinView(view, skinPairArray)
            skinView.applySkin()
            mSkinViews.add(skinView)
        }
    }
}