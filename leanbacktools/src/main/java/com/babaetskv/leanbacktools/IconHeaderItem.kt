package com.babaetskv.leanbacktools

import android.graphics.drawable.Drawable
import android.support.v17.leanback.widget.HeaderItem

/**
 * @author babaetskv on 16.07.19
 */

class IconHeaderItem(id: Long, name: String, private var icon: Drawable) : HeaderItem(id, name) {
    fun setIcon(icon: Drawable) {
        this.icon = icon
    }

    fun getIcon() = icon
}