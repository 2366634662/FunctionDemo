package utouu.functiondemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Du_Li on 2016/10/25.
 * Desc:不可以左右滑动的ViewPager
 */

public class MyViewPager extends ViewPager {

    private boolean isCanScroll = false;

    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isCanScroll) {
            return false;
        } else
            return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isCanScroll) {
            return false;
        } else
            return super.onInterceptTouchEvent(ev);
    }
}
