package utouu.functiondemo.framework.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Du_Li on 2016/10/25.
 * Desc:
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
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

    /**
     * init data
     */
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

    protected <T extends View> T findView(int id) {

        T view = (T) findViewById(id);

        return view;
    }

    protected void setOnClick(Object... objects) {

        for (Object object : objects) {
            if (object instanceof View) {
                ((View) object).setOnClickListener(this);
            }
            if (object instanceof Integer) {
                findView((int) object).setOnClickListener(this);
            }
        }

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
