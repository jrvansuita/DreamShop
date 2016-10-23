package com.vanhackathon.dreamshop.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by jrvansuita place 20/10/16.
 */

public class Animate implements Animation.AnimationListener {


    private Animation anim;


    private OnAnimBegin onStart;
    private OnAnimEnd onEnd;

    private View v;

    private Animate(View v) {
        this.v = v;

        anim = null;
    }

    public Animate(View v, int anim) {
        this(v);
        if (anim > 0) {
            this.anim = AnimationUtils.loadAnimation(v.getContext(), anim);
            this.anim.setAnimationListener(this);
        }
    }


    public Animate gone() {
        return setOnEnd(new OnAnimEnd() {
            @Override
            public void onAnimEnd() {
                v.setVisibility(View.GONE);
            }
        });
    }

    public Animate invisible() {
        return setOnEnd(new OnAnimEnd() {
            @Override
            public void onAnimEnd() {
                v.setVisibility(View.INVISIBLE);
            }
        });
    }

    public Animate visible() {
        if (v != null) {
            v.setVisibility(View.VISIBLE);
        }

        return this;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (onStart != null) {
            onStart.onAnimBegin();
        }

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (onEnd != null) {
            onEnd.onAnimEnd();
        }

    }

    public Animate setOnStart(OnAnimBegin onStart) {
        this.onStart = onStart;
        return this;
    }

    public Animate setOnEnd(OnAnimEnd onEnd) {
        this.onEnd = onEnd;
        return this;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    public static Animate builder(View view, int anim) {
        return new Animate(view, anim);
    }


    public void start() {
        if (anim != null)
            v.startAnimation(anim);

    }

    public void start(boolean visible) {
        if (anim != null) {
            if (visible) {
                if (v.getVisibility() != View.VISIBLE) {
                    v.setVisibility(View.VISIBLE);
                    v.startAnimation(anim);
                }
            } else {
                if (v.getVisibility() == View.VISIBLE) {
                    setOnEnd(new OnAnimEnd() {
                        @Override
                        public void onAnimEnd() {
                            v.setVisibility(View.GONE);
                        }
                    });

                    v.startAnimation(anim);
                }
            }

        }
    }

    public interface OnAnimBegin {
        void onAnimBegin();
    }

    public interface OnAnimEnd {
        void onAnimEnd();
    }


}
