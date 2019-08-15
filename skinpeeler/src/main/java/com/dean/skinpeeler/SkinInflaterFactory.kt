package com.dean.skinpeeler

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import java.lang.reflect.Constructor

/**
 * Created: tvt on 2019-08-13 20:24
 */
class SkinInflaterFactory : LayoutInflater.Factory2 {

    private val constructorMap = mutableMapOf<String, Constructor<out View>>()
    private val mConstructorSignature = arrayOf(Context::class.java, AttributeSet::class.java) // 两个参数的签名
    private val mClassPrefixList = arrayOf("android.widget.", "android.view.", "android.webkit.")

    private val skinAttributeSet = SkinAttribute()

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        var view = createViewFromTag(name, context, attrs)
        if (null == view) {
            view = createView(name, "", context, attrs)
        }
        skinAttributeSet.load(view!!, attrs)
        return view
    }

    override fun onCreateView(parent: String, context: Context, attrs: AttributeSet): View? {
        return null
    }

    private fun createViewFromTag(name: String, context: Context, attrs: AttributeSet): View? {
        if (-1 != name.indexOf(".")) {
            return null
        }
        var view: View? = null
        for (prefix in mClassPrefixList) {
            view = createView(name, prefix, context, attrs)
            if (null != view) {
                break
            }
        }
        return view
    }

    private fun createView(name: String, prefix: String, context: Context, attrs: AttributeSet): View? {
        var constructors = constructorMap[name]
        try {
            if (null == constructors) {
                val asSubclass = context.classLoader.loadClass(prefix + name).asSubclass(View::class.java)
                constructors = asSubclass.getConstructor(*mConstructorSignature)
                constructors.isAccessible = true
                constructorMap[name] = constructors
            }
            if (null != constructors) {
                return constructors.newInstance(context, attrs)
            }
        } catch (e: Exception) {
            Log.e(javaClass.name, e.toString())
        }
        return null
    }

}