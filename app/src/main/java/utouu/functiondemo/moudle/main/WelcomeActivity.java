package utouu.functiondemo.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;

import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.sharedpreferences.SharePrefreceHelper;
import utouu.functiondemo.framework.util.LogUtils;
import utouu.functiondemo.moudle.main.map.MapLocationUtils;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity implements Handler.Callback {

    private Handler mHandler;
    private ImageView iv_welcome_bg;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        iv_welcome_bg = findView(R.id.iv_welcome_bg);

    }

    @Override
    protected void initDatas() {
        mHandler = new Handler(this);
        iv_welcome_bg.setBackgroundResource(R.drawable.welcome_icon);
        mHandler.sendEmptyMessageDelayed(1, 2000);
        MapLocationUtils.getInstance()
                .regAndStartLocation(this)
                .setGetLocationDatas(new MapLocationUtils.GetLocationDatas() {
                    @Override
                    public void getLocationData(AMapLocation locationBean) {

                        LogUtils.e(locationBean.getAddress());

                    }

                    @Override
                    public void getErrorMsg(String errCode, String errorInfo) {

                    }
                });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case 1:
                if (SharePrefreceHelper.getInstence(this).getIsFirstIn()) {
                    startActivity(new Intent(this, GuideActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                finish();
                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MapLocationUtils.getInstance().stopLocation();
    }
}
