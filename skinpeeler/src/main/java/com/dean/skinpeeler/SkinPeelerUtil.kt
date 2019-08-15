package com.dean.skinpeeler

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources


/**
 * Created: tvt on 2019-08-13 19:29
 */
class SkinPeelerUtil {

    private var defaultResources: Resources? = null
    private var skinResources: Resources? = null
    private var skinPackageName: String? = null

    companion object {
        val instance: SkinPeelerUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SkinPeelerUtil()
        }
    }

    fun loadResource(context: Context, filePath: String) {
        val mPm = context.packageManager
        val mInfo = mPm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES)
        skinPackageName = mInfo!!.packageName
        defaultResources = context.resources

        val assetManager: AssetManager = AssetManager::class.java.newInstance()
        val addAssetPath = assetManager.javaClass.getMethod("addAssetPath", String::class.java)
        addAssetPath.invoke(assetManager, filePath)

        skinResources = Resources(assetManager, defaultResources?.displayMetrics, defaultResources?.configuration)
    }

    private fun getSkinResourcesId(resourcesId: Int): Int {
        val resourceEntryName = skinResources?.getResourceEntryName(resourcesId)
        val resourceTypeName = skinResources?.getResourceTypeName(resourcesId)

        val skinResourceId = skinResources?.getIdentifier(resourceEntryName, resourceTypeName, skinPackageName) ?: 0
        if (skinResourceId == 0) {
            return resourcesId
        }
        return skinResourceId
    }

    fun getSkinResourcesColor(resourcesId: Int): Int {
        val skinResourcesId = getSkinResourcesId(resourcesId)
        if (skinResourcesId == 0) {
            return defaultResources?.getColor(resourcesId) ?: 0
        }
        return skinResources?.getColor(skinResourcesId) ?: 0
    }

    fun getSkinResourcesBackground(resourcesId: Int): Any? {
        val skinResourcesId = getSkinResourcesId(resourcesId)
        if (skinResourcesId == 0) {
            return defaultResources?.getDrawable(resourcesId)
        }
        return skinResources?.getDrawable(skinResourcesId)
    }

}