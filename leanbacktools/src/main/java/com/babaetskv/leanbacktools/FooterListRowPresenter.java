package com.babaetskv.leanbacktools;

import android.content.Context;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @author babaetskv on 2019-06-14
 */
public class FooterListRowPresenter extends ListRowPresenter {
    private static final String FOOTER_FRAME_TAG = "FOOTER_FRAME_TAG";

    private Context context;

    public FooterListRowPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        super.onBindRowViewHolder(holder, item);
        LinearLayout container = (LinearLayout) holder.view;

        View currFooter = container.findViewWithTag(FOOTER_FRAME_TAG);
        if (currFooter != null)
            container.removeView(currFooter);

        if (item instanceof FooterListRow) {
            FooterListRow row = (FooterListRow) item;
            View footer = row.getFooter();
            if (footer == null)
                return;


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMarginStart(context.getResources().getDimensionPixelOffset(R.dimen.footer_margin_start));
            params.setMarginEnd(context.getResources().getDimensionPixelOffset(R.dimen.footer_margin_end));
            FrameLayout frame = new FrameLayout(context);
            frame.setTag(FOOTER_FRAME_TAG);
            container.addView(frame, params);
            frame.addView(footer);
        }
    }
}
