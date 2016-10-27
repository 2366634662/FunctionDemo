package utouu.functiondemo.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import utouu.functiondemo.MainActivity;
import utouu.functiondemo.R;
import utouu.functiondemo.bean.LoginBean;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.http.HttpCallBack;
import utouu.functiondemo.framework.http.HttpHelper;
import utouu.functiondemo.framework.util.LogUtils;
import utouu.functiondemo.framework.util.ToastUtil;

/**
 * 登陆页面
 */

public class LoginActivity extends BaseActivity{
    private EditText zc_name;
    private EditText zc_password;
    private Button login;//登录
    private TextView forget; // 忘记密码
    private  TextView zhuce;//注册

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        zc_name=(EditText)findViewById(R.id.zc_name);
        zc_password=(EditText)findViewById(R.id.zc_password);
        login=(Button)findViewById(R.id.login);
        forget = (TextView) findViewById(R.id.forget);
        zhuce = (TextView) findViewById(R.id.zhuce);

        login.setOnClickListener(this);
        forget.setOnClickListener(this);
        zhuce.setOnClickListener(this);

        zc_name.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    String username=zc_name.getText().toString().trim();
                    if(username.length()<4){
                        Toast.makeText(LoginActivity.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT);
                    }
                }
            }

        });
        zc_password.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(!hasFocus){
                    String password=zc_password.getText().toString().trim();
                    if(password.length()<4){
                        Toast.makeText(LoginActivity.this, "密码不能小于4个字符", Toast.LENGTH_SHORT);
                    }
                }
            }
        });
    }

    @Override
    protected void initDatas() {
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.zhuce:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                if(checkEdit())
                {
                    getDataLogin();
                }
                break;
            case R.id.forget:
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
                break;
    }
    }
        private boolean checkEdit(){
            if(zc_name.getText().toString().trim().equals("")){
                Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            }else if(zc_password.getText().toString().trim().equals("")){
                Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            }else{
                return true;
            }
            return false;
        }

    public void getDataLogin(){
        // 电话号码
        String phone = zc_name.getText().toString();
        // 密码
        String userpwd = zc_password.getText().toString();
        LoginBean login = new LoginBean();
        login.phoneNumber = phone;
        login.password = userpwd;
        Gson gson = new Gson();
        String where = gson.toJson(login);
        HttpHelper.getLogin(this, where, new HttpCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean result) {
                LogUtils.e("登录成功" + result);
                ToastUtil.showShortToast("恭喜您，登录成功！");
                //登录成功跳转到主页面
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            @Override
            public void onFail(String errMsg) {
                LogUtils.e("登录失败原因---》》》" + errMsg);
            }
        });
    }
}


