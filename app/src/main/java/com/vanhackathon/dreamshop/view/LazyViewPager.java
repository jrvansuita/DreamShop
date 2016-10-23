package com.vanhackathon.dreamshop.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class LazyViewPager  extends ViewPager {

    private static final int SCROLL_DURATION = 400;
    private int scrollDuration;
    private OnPageScrolled listenerScrolled;
    private OnChangeAdapter listenerAdapter;

    private OnScroll listenerScroll;
    private boolean pagingEnabled;


    private GestureDetector g;

    public LazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        setMyScroller();
        setScrollDuration(SCROLL_DURATION);
        setPagingEnabled(true);

        g = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            boolean wasLong = false;

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                if (!wasLong) {
                    if (onClickListener != null)
                        onClickListener.onClick(null);
                }
                wasLong = false;

                return true;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                // triggers first for both single tap and long press
                return true;
            }

            @Override
            public void onLongPress(MotionEvent event) {
                wasLong = true;
                super.onLongPress(event);
            }
        }

        );
    }

    public void setPagingEnabled(boolean pagingEnabled) {
        this.pagingEnabled = pagingEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.pagingEnabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        g.onTouchEvent(event);

        if (this.pagingEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    private void setMyScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getScrollDuration() {
        return scrollDuration;
    }

    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
    }


    private OnClickListener onClickListener;

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.onClickListener = l;
    }

    public class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, getScrollDuration());
        }
    }


    public int getCount() {
        return super.getAdapter().getCount();
    }


    @Override
    protected float getRightFadingEdgeStrength() {
        return super.getLeftFadingEdgeStrength();
    }

    @Override
    protected float getLeftFadingEdgeStrength() {
        return super.getLeftFadingEdgeStrength();
    }

    @Override
    protected void onPageScrolled(int arg0, float arg1, int arg2) {
        if (listenerScrolled != null) {
            listenerScrolled.onPageScrolled();
        }

        super.onPageScrolled(arg0, arg1, arg2);
    }

    public void setOnScrolled(OnPageScrolled listenerPageScrolled) {
        this.listenerScrolled = listenerPageScrolled;
    }

    public void setOnChangeAdapter(OnChangeAdapter listenerChangeAdapter) {
        this.listenerAdapter = listenerChangeAdapter;
    }

    @Override
    public void setAdapter(final PagerAdapter pager) {
        if (listenerAdapter != null) {
            listenerAdapter.onBeforeChangeAdapter(pager);
        }

        super.setAdapter(pager);

        if (listenerAdapter != null) {
            listenerAdapter.onAfterChangeAdapter(pager);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (listenerScroll != null) {
            listenerScroll.onScroll();
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setOnScroll(OnScroll listenerScroll) {
        this.listenerScroll = listenerScroll;
    }


    public interface OnScroll {
        void onScroll();
    }


    public interface OnChangeAdapter {
        void onBeforeChangeAdapter(PagerAdapter newAdapter);

        void onAfterChangeAdapter(PagerAdapter newAdapter);
    }

    public interface OnPageScrolled {
        void onPageScrolled();
    }



}
