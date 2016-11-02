package utouu.functiondemo.fragment;

import utouu.functiondemo.framework.base.BaseFragment;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseFragment;
import utouu.functiondemo.framework.util.LogUtils;
import utouu.functiondemo.view.ATDragView;
import utouu.functiondemo.view.CountDownCircleView;
import utouu.functiondemo.view.SendCodeButton;
import utouu.functiondemo.view.SignInSeekBar;
import utouu.functiondemo.view.SwitchView;

public class PageFourFragment extends BaseFragment {

    private SendCodeButton scb_code;//发送验证码
    private SignInSeekBar siseekbar_view;//签到
    private TextView tv_sign_in;//两条交互变换的线
    private SwitchView switchview1;//左右切换的按钮

    private ATDragView dragView;

    private CountDownCircleView countDownCircleView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        scb_code = findView(R.id.scb_code);

        siseekbar_view = findView(R.id.siseekbar_view);
        tv_sign_in = findView(R.id.tv_sign_in);
        switchview1 = findView(R.id.switchview1);
        dragView = findView(R.id.dragview);
        countDownCircleView = findView(R.id.countdown_circle_view);
        setOnClick(tv_sign_in, scb_code);

    }

    @Override
    protected void initData() {
        List<String> viewData = new ArrayList<>();
        viewData.add("周一");
        viewData.add("周二");
        viewData.add("周三");
        viewData.add("周四");
        viewData.add("周五");
        viewData.add("周六");
        viewData.add("周日");
        siseekbar_view.setSignInData(viewData);

        switchview1.setOpenState(true);//默认开启

        List<String> data = new ArrayList<>();
        data.add("0元");
        data.add("400元");
        data.add("800元");
        data.add("1600元");
        data.add("无限");
        dragView.setData(data, new ATDragView.OnDragFinishedListener() {
            @Override
            public void dragFinished(int leftPostion, int rightPostion) {

                LogUtils.e("回调数据Left-->" + leftPostion + "--Right-->" + rightPostion);

            }
        });

        countDownCircleView.startCountDown(new CountDownCircleView.OnCountDownFinishedListener() {
            @Override
            public void countDownStop() {
                Toast.makeText(getActivity(), "计时结束", Toast.LENGTH_LONG).show();
                LogUtils.e("计时结束");

            }
        });


    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_sign_in:
                siseekbar_view.signInEvent();
                break;
            case R.id.scb_code:
                scb_code.start();
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scb_code.stopConut();
    }

    @Override
    public void onResume() {
        super.onResume();
        scb_code.stopConut();
    }
}
