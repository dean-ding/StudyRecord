package com.dean.skinpeeler

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * 换肤
 * 主要替换  background，src，textColor
 */
class SkinView(private val view: View, private val skinPairs: MutableList<SkinPair>) {

    companion object {
        const val Background = "background"
        const val Src = "src"
        const val TextColor = "textColor"
    }

    fun applySkin() {
        for (skinPair in skinPairs) {
            when (skinPair.attrName) {
                Background -> {
                    val background = SkinPeelerUtil.instance.getSkinResourcesBackground(skinPair.resId)
                    background?.let {
                        if (background is Int) {
                            view.setBackgroundColor(background)
                        } else {
                            view.setBackgroundDrawable(background as Drawable)
                        }
                    }
                }
                Src -> {
                    val background = SkinPeelerUtil.instance.getSkinResourcesBackground(skinPair.resId)
                    background?.let {
                        if (background is Int) {
                            // 颜色背景
                            (view as ImageView).setImageDrawable(ColorDrawable(background))
                        } else {
                            (view as ImageView).setImageDrawable(background as Drawable)
                        }
                    }
                }
                TextColor -> {
                    val skinResourcesColor = SkinPeelerUtil.instance.getSkinResourcesColor(skinPair.resId)
                    if (0 != skinResourcesColor) {
                        (view as TextView).setTextColor(skinResourcesColor)
                    }
                }
            }
        }
    }
}