package utouu.functiondemo.framework.http;


import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import utouu.functiondemo.framework.UrlUtils;


/**
 * Created
 * Description:
 */
public class HttpHelper {

   //判断手机号
    public  static  void getIsExitPhone(Context context,String phone, HttpCallBack callBack){
        OkHttpUtils
                .get()
                .url(UrlUtils.SMART_IFEXIST)
                .addParams("key", "1")
                .addParams("phone", phone)
                .build()
                .execute(callBack);

    }
    //注册
    public  static  void getRegister(Context context,String where, HttpCallBack callBack){
        OkHttpUtils
                .get()
                .url(UrlUtils.SMART_IFEXIST)
                .addParams("key", "1")
                .addParams("where", where)
                .build()
                .execute(callBack);

    }
    //登录
    public  static  void getLogin(Context context,String where, HttpCallBack callBack){
        OkHttpUtils
                .get()
                .url(UrlUtils.SMART_LOGIN)
                .addParams("key", "1")
                .addParams("where", where)
                .build()
                .execute(callBack);

    }

}
