package utouu.functiondemo.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.sharedpreferences.SharePrefreceHelper;
import utouu.functiondemo.moudle.main.other.FingerVerify;

public class FingerActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_finger;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Button btn_finger = (Button) findViewById(R.id.btn_activity_main_finger);
        btn_finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharePrefreceHelper.getInstence(FingerActivity.this).getIsFinger()) {
                    FingerVerify.getInstance(FingerActivity.this);
                } else {
                    startActivity(new Intent(FingerActivity.this, GuideActivity.class));
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

    }
}
