package utouu.functiondemo.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseActivity;

/**
 * 登陆页面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.zc_name)
    EditText zcName;
    @BindView(R.id.zc_password)
    EditText zcPassword;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.login)
    Button login;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.zhuce, R.id.forget, R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.forget:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.login:

                break;
        }
    }
}
