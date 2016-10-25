package utouu.functiondemo.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Du_Li on 2016/10/25.
 * Desc:
 */

public abstract class BaseActivity extends FragmentActivity {
    /**
     * get activity layout
     */
    @LayoutRes
    protected abstract int getLayout();


    /**
     * init activity view
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initDatas();

    /*
       * load data in onResume
       */
    protected abstract void loadData();

    protected Activity mContext;
    protected boolean mIsFirstShow = true;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        try {
//            EventBus.getDefault().register(this);
//        } catch (NoClassDefFoundError ignored) {
//        }
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        initDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        try {
//            EventBus.getDefault().unregister(this);
//        } catch (NoClassDefFoundError ignored) {
//        }
        mUnbinder.unbind();
    }

    @Override
    protected void onResume() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            loadData();
        }
        super.onResume();
    }

}
