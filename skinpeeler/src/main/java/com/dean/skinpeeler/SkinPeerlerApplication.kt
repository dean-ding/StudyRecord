package com.dean.skinpeeler

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import java.io.File


/**
 * Created: tvt on 2019-08-14 16:00
 */
class SkinPeerlerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val externalAppDataPath = PathUtils.getExternalStoragePath()
        val fileName = externalAppDataPath + File.separator + "yellow_skin.skin"
        if (FileUtils.isFileExists(fileName)) {
//            SkinPeelerUtil.instance.loadResource(
//                this@SkinPeerlerApplication,
//                fileName
//            )
        }

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                val inflater = LayoutInflater.from(activity)
                try {
                    val field = LayoutInflater::class.java.getDeclaredField("mFactorySet")
                    field.isAccessible = true
                    field.setBoolean(inflater, false)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val skinFactory = SkinInflaterFactory()
                inflater.factory2 = skinFactory
            }

        })
    }
}