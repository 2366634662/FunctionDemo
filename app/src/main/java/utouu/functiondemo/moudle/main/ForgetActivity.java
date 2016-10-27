package utouu.functiondemo.moudle.main;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import utouu.functiondemo.R;
import utouu.functiondemo.bean.RegistBean;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.http.HttpCallBack;
import utouu.functiondemo.framework.http.HttpHelper;
import utouu.functiondemo.framework.util.LogUtils;
import utouu.functiondemo.framework.util.ToastUtil;

public class ForgetActivity extends BaseActivity implements Handler.Callback{
    ImageView fanhui;
    Button next;
    // 手机号码
    String phone;
    EditText zc_phone_txt;
    // 发送验证码
    Button zc_send;
    // 时间设置
    private int time = 60;
    private Handler mHandler;
    private boolean showPwdFlag = false;// false 隐藏 true 显示
    // 验证码
    EditText zc_key_txt;
    // 发送的验证码
    String yz_code;
    @Override
    protected int getLayout() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fanhui = (ImageView) findViewById(R.id.fanhui);
        next = (Button) findViewById(R.id.next);
        zc_send = (Button) findViewById(R.id.zc_send);
        zc_phone_txt = (EditText) findViewById(R.id.zc_phone_txt);
        zc_key_txt = (EditText) findViewById(R.id.zc_key_txt);
        zc_send.setOnClickListener(this);
        fanhui.setOnClickListener(this);
        next.setOnClickListener(this);

        zc_send.setText("获取验证码");
        mHandler = new Handler(this);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void loadData() {

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
                LogUtils.e("判断手机号码返回结果" + result);
                if (result != null) {
                    if (result.status.equals("1")) {
                        getDataRegisterTask();

                    } else if (result.status.equals("2")) {
                        ToastUtil.showShortToast("手机号码已经注册");
                    }
                }
            }

            @Override
            public void onFail(String errMsg) {
                LogUtils.e("判断手机号码返回的错误信息" + errMsg);
            }
        });
    }

    /**
     * 验证码  是第二次网络请求
     */
    public void getDataRegisterTask() {
        // 手机
        phone = zc_phone_txt.getText().toString();
        HttpHelper.getIsExitPhone(this, phone, new HttpCallBack<RegistBean>() {
            @Override
            public void onSuccess(RegistBean result) {
                LogUtils.e("发送验证码成功---》》》" + result);
                yz_code = result.code;
                mHandler.sendEmptyMessage(1);

                ToastUtil.showShortToast("验证码发送成功 ！");
                //发送验证码不可点击
                zc_send.setClickable(false);
                zc_phone_txt.setEnabled(false);
            }

            @Override
            public void onFail(String errMsg) {
                ToastUtil.showShortToast("验证码发送失败，请重试 ！");
                LogUtils.e("发送验证码失败---》》》" + errMsg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.next:
                // 输入的验证码
                String zc_code = zc_key_txt.getText().toString();
                if (zc_code.equals(yz_code)) {
                   // startActivity(new Intent(ForgetActivity.this, UpdatepwdActivity.class));
                    Log.i("zc_code", zc_code);
                } else {
                    Toast.makeText(ForgetActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.zc_send:
                phone = zc_phone_txt.getText().toString();
                if (!phone.equals("")) {
                    getDataIsExit();
                } else {
                    Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
                }
                break;
        }

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
