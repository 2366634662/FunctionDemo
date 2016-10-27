package utouu.functiondemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Du_Li on 2016/9/8.
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }
}
