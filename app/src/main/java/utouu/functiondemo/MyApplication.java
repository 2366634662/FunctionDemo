package utouu.functiondemo;

import android.app.Application;
import android.content.Context;

import utouu.functiondemo.framework.sharedpreferences.SharePrefreceHelper;
import utouu.functiondemo.framework.util.LogUtils;

/**
 * Created by Du_Li on 2016/9/8.
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;
    public static final String HAS_FINGERPRINT_API = "hasFingerPrintApi";
    public static final String SETTINGS = "settings";

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        if (SharePrefreceHelper.getInstence(this).contains(HAS_FINGERPRINT_API)) { // 检查是否存在该值，不必每次都通过反射来检查
            return;
        }
        try {
            LogUtils.e("try里面");
            Class.forName("android.hardware.fingerprint.FingerprintManager"); // 通过反射判断是否存在该类
            SharePrefreceHelper.getInstence(this).setIsFinger(true);
        } catch (ClassNotFoundException e) {
            LogUtils.e("catch里面有错误信息");
            SharePrefreceHelper.getInstence(this).setIsFinger(false);
            e.printStackTrace();
        }
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

}
