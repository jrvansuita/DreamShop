package com.vanhackathon.dreamshop.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.vanhackathon.dreamshop.R;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class PageMarker extends LinearLayout implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager pager;
    private ViewPager.OnPageChangeListener pageChangeListener;
    private int horizontalPadding;
    private int verticalPadding;
    private int indicatorWidth;
    private int indicatorHeight;

    private int animatorId;
    private int selectedDrawable;
    private int unselectedDrawable;

    private int currentPosition;
    private Animator animOut;
    private Animator animIn;

    public PageMarker(Context context) {
        this(context, null);
    }

    public PageMarker(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PageMarker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (!isInEditMode()) {
            this.init(context, attrs);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        this.animatorId = R.animator.scale_with_alpha;
        this.currentPosition = 0;

        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setGravity(17);
        this.handleTypedArray(context, attrs);
        this.animOut = AnimatorInflater.loadAnimator(context, this.animatorId);
        this.animIn = AnimatorInflater.loadAnimator(context, this.animatorId);
        this.animIn.setInterpolator(new ReverseInterpolator());
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PageMarker, 0, 0);

            try {
                this.horizontalPadding = typedArray.getDimensionPixelSize(R.styleable.PageMarker_horizontal_padding, this.dip2px(5.0F));
                this.verticalPadding = typedArray.getDimensionPixelSize(R.styleable.PageMarker_vertical_padding, this.dip2px(5.0F));
                this.indicatorWidth = typedArray.getDimensionPixelSize(R.styleable.PageMarker_circle_width, this.dip2px(5.0F));
                this.indicatorHeight = typedArray.getDimensionPixelSize(R.styleable.PageMarker_circle_height, this.dip2px(5.0F));
                this.animatorId = typedArray.getResourceId(R.styleable.PageMarker_animator, R.animator.scale_with_alpha);
                this.selectedDrawable = typedArray.getResourceId(R.styleable.PageMarker_drawable_selected, R.drawable.bluish_circle);
                this.unselectedDrawable = typedArray.getResourceId(R.styleable.PageMarker_drawable_unselected, R.drawable.bluish_circle_a);
            } finally {
                typedArray.recycle();
            }

        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.pager = viewPager;
        this.currentPosition = this.pager.getCurrentItem();
        this.createIndicators(viewPager);
        this.pager.addOnPageChangeListener(this);
        this.onPageSelected(this.currentPosition);
    }

    public void refresh(){
        setViewPager(pager);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (this.pager == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        } else {
            this.pageChangeListener = onPageChangeListener;
            this.pager.addOnPageChangeListener(this);
        }
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (this.pageChangeListener != null) {
            this.pageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

    }

    public void onPageSelected(int position) {
        if (this.pageChangeListener != null) {
            this.pageChangeListener.onPageSelected(position);
        }

        if (this.animIn.isRunning()) {
            this.animIn.end();
        }

        if (this.animOut.isRunning()) {
            this.animOut.end();
        }

        ViewGroup frame = (ViewGroup) this.getChildAt(this.currentPosition);
        if (frame != null) {
            View currentIndicator = frame.getChildAt(0);
            currentIndicator.setBackgroundResource(this.unselectedDrawable);
            this.animIn.setTarget(currentIndicator);
            this.animIn.start();
            View selectedIndicator = ((FrameLayout) this.getChildAt(position)).getChildAt(0);
            selectedIndicator.setBackgroundResource(this.selectedDrawable);
            this.animOut.setTarget(selectedIndicator);
            this.animOut.start();
            this.currentPosition = position;
        }
    }

    public void onPageScrollStateChanged(int state) {
        if (this.pageChangeListener != null) {
            this.pageChangeListener.onPageScrollStateChanged(state);
        }

    }

    private void createIndicators(ViewPager viewPager) {
        this.removeAllViews();
        int count = viewPager.getAdapter().getCount();
        if (count > 0) {
            this.addIndicator(this.selectedDrawable, this.animOut);

            for (int i = 1; i < count; ++i) {
                this.addIndicator(this.unselectedDrawable, this.animIn);
            }

        }
    }

    private void addIndicator(int backgroundDrawableId, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
        }

        FrameLayout holder = new FrameLayout(getContext());
        holder.setOnClickListener(this);
        holder.setTag(this.getChildCount());
        this.addView(holder, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);

        View indicator = new View(this.getContext());
        holder.addView(indicator, this.indicatorWidth, this.indicatorHeight);
        indicator.setBackgroundResource(backgroundDrawableId);
        animator.setTarget(indicator);
        animator.start();
    }

    public int dip2px(float dpValue) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    @Override
    public void onClick(View v) {
        pager.setCurrentItem((Integer) v.getTag());
    }

    class ReverseInterpolator implements Interpolator {
        private ReverseInterpolator() {
        }

        public float getInterpolation(float value) {
            return Math.abs(1.0F - value);
        }

    }


}
