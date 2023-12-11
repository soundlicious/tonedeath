package dev.pabloexposito.common

import android.annotation.SuppressLint
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.pabloexposito.model.data.PinYinSoundInfo

class AppPinyinResourceResolver(
    @ApplicationContext private val context: Context
) : ResourceResolver<PinYinSoundInfo> {
    private val resourceType = "raw"

    @SuppressLint("DiscouragedApi")
    override fun invoke(resInfo: PinYinSoundInfo) = with(resInfo) {
        val resourceName = audioResName
        kotlin.runCatching {
            context.resources.getIdentifier(resourceName, resourceType, context.packageName)
        }
    }
}