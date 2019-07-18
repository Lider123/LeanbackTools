package com.babaetskv.leanbacktools;

import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.view.View;

/**
 * @author babaetskv on 2019-06-14
 */
public class FooterListRow extends ListRow {
    private View footer;

    public FooterListRow(HeaderItem header, ObjectAdapter adapter, View footer) {
        super(header, adapter);
        this.footer = footer;
    }

    View getFooter() {
        return footer;
    }
}
