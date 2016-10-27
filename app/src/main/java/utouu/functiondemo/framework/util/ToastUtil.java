package utouu.functiondemo.framework.util;

import android.widget.Toast;

import utouu.functiondemo.MyApplication;


/**
 * Created by Du_Li on 2016/9/8.
 */
public class ToastUtil {
    //避免多次显示吐司通知
    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void showShortToast(String info) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), info, Toast.LENGTH_SHORT);
        } else {
            toast.setText(info);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void showLongToast(String info) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), info, Toast.LENGTH_LONG);
        } else {
            toast.setText(info);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShortToast(int resId) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), MyApplication.getAppContext().getString(resId), Toast.LENGTH_SHORT);
        } else {
            toast.setText(MyApplication.getAppContext().getString(resId));
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLongToast(int resId) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getAppContext(), MyApplication.getAppContext().getString(resId), Toast.LENGTH_LONG);
        } else {
            toast.setText(MyApplication.getAppContext().getString(resId));
        }
        toast.show();
    }

}
