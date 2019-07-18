package com.babaetskv.leanbacktools

import android.content.Context
import android.support.v17.leanback.widget.ListRowPresenter
import android.support.v17.leanback.widget.RowPresenter
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

/**
 * @author babaetskv on 16.07.19
 */
class HintableListRowPresenter(private val context: Context) : ListRowPresenter() {
    private val HINT_TAG = "HINT"

    override fun onBindRowViewHolder(holder: RowPresenter.ViewHolder, item: Any) {
        super.onBindRowViewHolder(holder, item)
        val container = holder.view as LinearLayout
        if (item is HintableListRow) {
            container.findViewWithTag<View>(HINT_TAG)?:run {
                container.addView(TextView(context).apply {
                    val paddingLeft = resources.getDimensionPixelOffset(R.dimen.hint_padding_left)
                    val paddingVertical = resources.getDimensionPixelOffset(R.dimen.hint_padding_vertical)
                    setPadding(paddingLeft, paddingVertical, 0, paddingVertical)
                    text = item.getHint()
                    textSize = 22f
                    tag = HINT_TAG
                })
            }

            val rowsView = container.getChildAt(0)
            val hintView = container.findViewWithTag<View>(HINT_TAG)
            if (item.hintEnabled()) {
                rowsView.visibility = View.GONE
                hintView.visibility = View.VISIBLE
            } else {
                rowsView.visibility = View.VISIBLE
                hintView.visibility = View.GONE
            }
        } else {
            val hintView = container.findViewWithTag<View>(HINT_TAG)
            hintView?.let { container.removeView(it) }
        }
    }
}
