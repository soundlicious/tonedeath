package dev.expositopablo.tonedeath.androidutils

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable

object ImagesUtils {

    //https://stackoverflow.com/a/14128907/5946253
    fun convertDrawableToGrayScale(drawable: Drawable?): Drawable? {
        if (drawable == null) {
            return null
        }
        val res = drawable.mutate()
        res.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
        return res
    }
}
