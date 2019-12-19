package com.kjs.fishertiger.jelly_android_master.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CollectionViewPager extends ViewPager {

    private boolean scrollable = true;

    public CollectionViewPager(Context context) {
        super(context);
    }

    public CollectionViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean enable) {
        scrollable = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(scrollable){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(scrollable){
            return super.onTouchEvent(ev);
        }else{
            return false;
        }
    }
}
