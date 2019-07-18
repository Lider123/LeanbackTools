package com.babaetskv.leanbacktools

import android.support.v17.leanback.widget.Action
import android.support.v17.leanback.widget.Presenter
import android.support.v17.leanback.widget.PresenterSelector
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

/**
 * @author babaetskv on 16.07.19
 */

class ExtendedActionPresenterSelector : PresenterSelector() {

    private val mNoLineActionPresenter = NoLineActionPresenter()
    private val mOneLineActionPresenter = OneLineActionPresenter()
    private val mTwoLineActionPresenter = TwoLineActionPresenter()
    private val mPresenters = arrayOf(mNoLineActionPresenter, mOneLineActionPresenter, mTwoLineActionPresenter)

    override fun getPresenter(item: Any): Presenter {
        item as Action
        return if (item.label1.isNullOrEmpty() && item.label2.isNullOrEmpty())
            mNoLineActionPresenter
        else if (item.label2.isNullOrEmpty())
            mOneLineActionPresenter
        else
            mTwoLineActionPresenter
    }

    override fun getPresenters(): Array<Presenter> = mPresenters

    internal class ActionViewHolder(view: View, val mLayoutDirection: Int) : Presenter.ViewHolder(view) {
        var mAction: Action? = null
        val mButton: Button = view.findViewById(R.id.lb_action_button)
    }

    internal class LabellessActionViewHolder(view: View) : Presenter.ViewHolder(view) {
        var mAction: Action? = null
        val mButton: ImageButton = view.findViewById(R.id.action_image_button)
    }

    internal class NoLineActionPresenter : Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.action_0_line, parent, false)
            return LabellessActionViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
            item as Action
            viewHolder as LabellessActionViewHolder
            viewHolder.mAction = item
            viewHolder.mButton.setImageDrawable(item.icon)
        }

        override fun onUnbindViewHolder(viewHolder: ViewHolder) {
            viewHolder as LabellessActionViewHolder
            viewHolder.mAction = null
        }
    }

    internal class OneLineActionPresenter : Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.lb_action_1_line, parent, false)
            return ActionViewHolder(v, parent.layoutDirection)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
            item as Action
            viewHolder as ActionViewHolder
            viewHolder.mAction = item
            viewHolder.mButton.text = item.label1
        }

        override fun onUnbindViewHolder(viewHolder: ViewHolder) {
            viewHolder as ActionViewHolder
            viewHolder.mAction = null
        }
    }

    internal class TwoLineActionPresenter : Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.lb_action_2_lines, parent, false)
            return ActionViewHolder(v, parent.layoutDirection)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
            item as Action
            viewHolder as ActionViewHolder
            val icon = item.icon
            viewHolder.mAction = item

            icon?.let {
                val startPadding = viewHolder.view.resources
                        .getDimensionPixelSize(R.dimen.lb_action_with_icon_padding_start)
                val endPadding = viewHolder.view.resources
                        .getDimensionPixelSize(R.dimen.lb_action_with_icon_padding_end)
                viewHolder.view.setPaddingRelative(startPadding, 0, endPadding, 0)
            } ?: run {
                val padding = viewHolder.view.resources
                        .getDimensionPixelSize(R.dimen.lb_action_padding_horizontal)
                viewHolder.view.setPaddingRelative(padding, 0, padding, 0)
            }
            if (viewHolder.mLayoutDirection == View.LAYOUT_DIRECTION_RTL)
                viewHolder.mButton.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
            else
                viewHolder.mButton.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null)

            val line1 = item.label1
            val line2 = item.label2
            when {
                TextUtils.isEmpty(line1) -> viewHolder.mButton.text = line2
                TextUtils.isEmpty(line2) -> viewHolder.mButton.text = line1
                else -> viewHolder.mButton.text = "$line1\n$line2"
            }
        }

        override fun onUnbindViewHolder(viewHolder: ViewHolder) {
            viewHolder as ActionViewHolder
            viewHolder.mButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            viewHolder.view.setPadding(0, 0, 0, 0)
            viewHolder.mAction = null
        }
    }
}