package utouu.functiondemo.framework;

/**
 * Created by Du_Li on 2016/10/26.
 * Desc:url地址类
 */

public class UrlUtils {


    public static final String BASE_URL = "";


    // 服务器
    public static final String SOULOFER_HEAD = "http://z-jc.cn/";

    // 注册
    public static final String SMART_REGISTER = SOULOFER_HEAD + "Api/User/update";
    // 短信验证PHP
    public static final String SMART_SMS = SOULOFER_HEAD + "Api/User/seng_message";
    // 验证手机号码是否已经注册
    public static final String SMART_IFEXIST = SOULOFER_HEAD + "Api/User/yzphone";
    // 登录
    public static final String SMART_LOGIN = SOULOFER_HEAD + "Api/User/one";
    // 邀请的朋友
    public static final String MY_FRIEND = "http://115.29.176.130/yunhuitong/myFriend.php";

}
