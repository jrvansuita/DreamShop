package com.vanhackathon.dreamshop.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class SwipeToRefreshLayout extends android.support.v4.widget.SwipeRefreshLayout {

    public SwipeToRefreshLayout(Context context) {
        super(context);
    }

    public SwipeToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    View scrollableView = null;


    public void setScrollableView(View view) {
        scrollableView = view;
    }

    @Override
    public boolean canChildScrollUp() {
        if (scrollableView == null)
            return false;
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (scrollableView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) scrollableView;
                return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return scrollableView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(scrollableView, -1);
        }
    }
}