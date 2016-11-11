package utouu.functiondemo.framework.sharedpreferences;

import android.content.Context;

/**
 * Created by Du_Li on 2016/9/19.20:17
 */
public class SharePrefreceHelper extends SPUtils {
    private static SharePrefreceHelper sharePrefreceHelper;

    /**
     * SPUtils构造函数
     *
     * @param context 上下文
     */
    private SharePrefreceHelper(Context context) {
        super(context);
    }

    public static SharePrefreceHelper getInstence(Context context) {
        if (sharePrefreceHelper == null)
            sharePrefreceHelper = new SharePrefreceHelper(context);
        return sharePrefreceHelper;
    }

    //使用
    public void setUserName(String userName) {
        putString("userName", userName);
    }

    public String getUserName() {
        return getString("userName");
    }

    public void setIsFirstIn(boolean isFirstIn) {
        putBoolean("isFirstIn", isFirstIn);
    }

    public boolean getIsFirstIn() {
        return getBoolean("isFirstIn", true);
    }

    public void setIsFinger(boolean isFirstIn) {
        putBoolean("isFinger", isFirstIn);
    }

    public boolean getIsFinger() {
        return getBoolean("isFinger", true);
    }


}
