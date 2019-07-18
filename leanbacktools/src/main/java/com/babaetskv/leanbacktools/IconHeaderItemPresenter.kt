package com.babaetskv.leanbacktools

import android.content.Context
import android.support.v17.leanback.widget.Presenter
import android.support.v17.leanback.widget.Row
import android.support.v17.leanback.widget.RowHeaderPresenter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * @author babaetskv on 16.07.19
 */
class IconHeaderItemPresenter : RowHeaderPresenter() {
    private var mUnselectedAlpha = 0f

    override fun onCreateViewHolder(viewGroup: ViewGroup): ViewHolder {
        mUnselectedAlpha = viewGroup.resources
                .getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1)
        val inflater = viewGroup.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.icon_header_item, null)
        view.alpha = mUnselectedAlpha // Initialize icons to be at half-opacity.

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any?) {
        item as Row

        val headerItem = item.headerItem
        val rootView = viewHolder.view
        rootView.isFocusable = true

        val iconView = rootView.findViewById<ImageView>(R.id.header_icon)
        if (headerItem is IconHeaderItem) {
            val icon = headerItem.getIcon()
            iconView.setImageDrawable(icon)
        }
        else
            iconView.visibility = View.GONE

        val label = rootView.findViewById<TextView>(R.id.header_label)
        label.text = headerItem.name
    }

    override fun onSelectLevelChanged(holder: ViewHolder) {
        holder.view.alpha = mUnselectedAlpha + holder.selectLevel * (1.0f - mUnselectedAlpha)
    }
}
