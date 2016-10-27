package utouu.functiondemo.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import utouu.functiondemo.R;
import utouu.functiondemo.bean.LoginInfoBean;
import utouu.functiondemo.bean.RegistBean;
import utouu.functiondemo.bean.ZhuceDataBean;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.http.HttpCallBack;
import utouu.functiondemo.framework.http.HttpHelper;
import utouu.functiondemo.framework.util.LogUtils;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity implements Handler.Callback {
    private String newName = "image.jpg";
    private String actionUrl = "http://192.168.0.71:8086/HelloWord/myForm";
    // 头像
    String head;
    // 昵称
    private EditText zc_name;
    // 手机号输入框
    private static EditText zc_phone_txt;
    private   String phone;

    // 验证码输入框
    private EditText zc_key_txt;
    // 获取验证码按钮
    private Button zc_send;
    // 密码
    private EditText zc_password;
    // 支付密码
    private EditText zf_pwd;

    // 注册按钮
    private Button zc_register;
    // 输入验证码
    String yzcode;
    // 返回的验证码
    String code;
    String status;
    // 时间设置
    private int time = 60;
    private Handler mHandler;
    private boolean showPwdFlag = false;// false 隐藏 true 显示
    LoginInfoBean loginInfoBean;
    // 返回
    ImageView fanhui;
    ImageView yanjing;


    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        zc_send = (Button) findViewById(R.id.zc_send);
        zc_register = (Button) findViewById(R.id.zc_register);
        zc_phone_txt = (EditText) findViewById(R.id.zc_phone_txt);
        zc_key_txt = (EditText) findViewById(R.id.zc_key_txt);
        zc_name = (EditText) findViewById(R.id.zc_name);
        zc_password = (EditText) findViewById(R.id.zc_password);
        zf_pwd = (EditText) findViewById(R.id.zfpwd);
        fanhui = (ImageView) findViewById(R.id.fanhui);
        yanjing = (ImageView) findViewById(R.id.yanjing);
        yanjing.setOnClickListener(this);
        fanhui.setOnClickListener(this);
        zc_send.setOnClickListener(this);
        zc_register.setOnClickListener(this);
        zc_send.setText("获取验证码");
        mHandler = new Handler(this);

//        //点击后设置为不可点击  以免再次发送验证码
//        zc_send.setClickable(false);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void loadData() {

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // 发送验证码
            case R.id.zc_send:
                // 手机号码
                String phoneNums = zc_phone_txt.getText().toString();

                /**
                 * 1.如果手机号码为空和不是正确号码，就提示错误，如果正确则发送验证码
                 */
                // 1. 通过规则判断手机号

                if (!judgePhoneNums(phoneNums)) {
                    Toast.makeText(this, "手机号码输入不正确", Toast.LENGTH_SHORT).show();
                } else {
                    getDataIsExit();  //是第一次网络请求
                }

                break;
            case R.id.zc_register:
                getDataRegist();
                break;
            case R.id.fanhui:
                finish();
                break;
            case R.id.yanjing:
                if (showPwdFlag) {
                    showPwdFlag = false;
                    zc_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    yanjing.setImageResource(R.drawable.yanjing_close);
                } else {
                    showPwdFlag = true;
                    zc_password.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD));
                    yanjing.setImageResource(R.drawable.yanjing);
                }
                break;
        }

    }

    /**
     * 判断手机号码是否存在  是第一次网络请求
     */
    public void getDataIsExit() {
        // 手机号码
        phone = zc_phone_txt.getText().toString();

        HttpHelper.getIsExitPhone(this, phone, new HttpCallBack<RegistBean>() {
            @Override
            public void onSuccess(RegistBean result) {
                LogUtils.e("判断手机号码", result+"");
                Gson gson = new Gson();
                RegistBean regist = gson.fromJson(result.status, RegistBean.class);
                status = regist.status;
                if (status.equals("1")) {
                    getDataRegisterTask();

                } else if (status.equals("2")) {
                    Toast.makeText(RegisterActivity.this, "手机号码已注册！", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFail(String errMsg) {
            }
        });
    }

    /**
     * 验证码  是第二次网络请求
     */
    public void getDataRegisterTask(){

        // 手机
        phone = zc_phone_txt.getText().toString();
        HttpHelper.getIsExitPhone(this, phone, new HttpCallBack<RegistBean>() {
            @Override
            public void onSuccess(RegistBean result) {
                LogUtils.e("发送验证码成功", result + "");
                Gson gson = new Gson();
                RegistBean regist = gson.fromJson(result.code, RegistBean.class);
                code = regist.code;
                mHandler.sendEmptyMessage(1);
                zc_phone_txt.setEnabled(false);
            }
            @Override
            public void onFail(String errMsg) {

            }
        });
    }
    /**
     * 注册 是第三次网络请求
     */
    public void getDataRegist(){
        // 昵称
        String username = zc_name.getText().toString();
        // 手机号码
        String phone = zc_phone_txt.getText().toString();
        // 密码
        String userpwd = zc_password.getText().toString();
        // 支付密码
        String zfpwd = zf_pwd.getText().toString();
        // 输入的验证码
        yzcode = zc_key_txt.getText().toString();
        ZhuceDataBean data = new ZhuceDataBean();
        data.username = username;
        data.phone = phone;
        data.userpwd = userpwd;
        data.zfpwd = zfpwd;
        Gson gson = new Gson();
        String where = gson.toJson(data);
        if (code != null && code.equals(yzcode)) {
            zc_register.setBackgroundResource(R.drawable.bg_red_radius5);
            zc_register.setEnabled(true);
            if(!username.equals("") && !phone.equals("") && !userpwd.equals("")&& !zfpwd.equals("")){
                HttpHelper.getRegister(this, where, new HttpCallBack<ZhuceDataBean>() {
                    @Override
                    public void onSuccess(ZhuceDataBean result) {
                        LogUtils.e("注册成功", result + "");
                        Toast.makeText(RegisterActivity.this, "恭喜您，注册成功！", Toast.LENGTH_SHORT).show();
                        // 注册成功跳转到登录界面
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }
                    @Override
                    public void onFail(String errMsg) {
                    }
                });
            }else {
                Toast.makeText(this, "存在错误信息", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "没有发送验证码或验证码不正确！", Toast.LENGTH_SHORT).show();
            zc_register.setBackgroundResource(R.drawable.gray_solid_circle_shape);
            zc_register.setEnabled(false);
        }
    }

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11) && isMobileNO(phoneNums)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }
    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                //延迟消息发送到这里接受   让time每次减一
                time--;
                zc_send.setText("" + time + "S后重新获取");
                //判断  如果当time = 0时  将按钮的状态设置为可以重新点击
                //将给time 赋值等于60
                //发送一个为  2  的消息  跳出发循环消息的状态
                if (time == 0) {
                    time = 60;
                    zc_send.setText("重新获取验证码");
                    zc_send.setClickable(true);//设置为可点击
                    mHandler.sendEmptyMessageDelayed(2, 1000);
                } else {
                    //如果time 不等于 0   执行发循环消息
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                }
                break;
            case 2:
                //接收发送为2 的消息  将handler中所有的发送消息的状态移除
                //避免一直在发无用的消息
                mHandler.removeCallbacksAndMessages(null);
                break;
        }
        return false;
    }
    //程序退出  将消息移空  避免APP一直在后台发送无用消息
    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mHandler.removeCallbacksAndMessages(null);
        super.onPause();
    }

}
