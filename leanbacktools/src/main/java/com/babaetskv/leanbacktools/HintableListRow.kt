package com.babaetskv.leanbacktools

import android.support.v17.leanback.widget.HeaderItem
import android.support.v17.leanback.widget.ListRow
import android.support.v17.leanback.widget.ObjectAdapter

/**
 * @author babaetskv on 16.07.19
 */

class HintableListRow(header: HeaderItem, adapter: ObjectAdapter, private var hint: String) : ListRow(header, adapter) {
    private var hintEnabled: Boolean = false

    fun hintEnabled() = hintEnabled

    fun setHintEnabled(hintEnabled: Boolean) {
        this.hintEnabled = hintEnabled
    }

    fun getHint() = hint

    fun setHint(hint: String) {
        this.hint = hint
    }
}