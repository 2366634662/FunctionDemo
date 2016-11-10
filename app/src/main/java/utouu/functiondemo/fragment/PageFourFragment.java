package utouu.functiondemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseFragment;
import utouu.functiondemo.framework.util.LogUtils;
import utouu.functiondemo.framework.util.ToastUtil;
import utouu.functiondemo.moudle.main.AnimatorActivity;
import utouu.functiondemo.view.ATDragView;
import utouu.functiondemo.view.CountDownCircleView;
import utouu.functiondemo.view.SendCodeButton;
import utouu.functiondemo.view.SignInSeekBar;
import utouu.functiondemo.view.SwitchView;
import utouu.functiondemo.view.circleview.CircleMenu;
import utouu.functiondemo.view.circleview.OnMenuSelectedListener;
import utouu.functiondemo.view.circleview.OnMenuStatusChangeListener;

public class PageFourFragment extends BaseFragment {

    private SendCodeButton scb_code;//发送验证码
    private SignInSeekBar siseekbar_view;//仿签到
    private TextView tv_sign_in;//两条交互变换的线
    private SwitchView switchview1;//左右切换的按钮

    private ATDragView dragView;//在一条线上有两个球可以左右拖动的View

    private CountDownCircleView countDownCircleView;

    private Button btn_animator;

    private CircleMenu cm_menu;//圆形菜单
    private int[] iconResArray = new int[5];

    {
        iconResArray[0] = R.mipmap.nav_icon_home_sel;
        iconResArray[1] = R.mipmap.nav_icon_my_sel;
        iconResArray[2] = R.mipmap.nav_icon_order_sel;
        iconResArray[3] = R.mipmap.nav_icon_server_sel;
        iconResArray[4] = R.mipmap.nav_icon_server_sel;
    }

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
        btn_animator = findView(R.id.btn_animator);
        cm_menu = findView(R.id.cm_menu);
        setOnClick(tv_sign_in, scb_code, btn_animator);

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


        cm_menu.setMainIconResource(R.drawable.qq, R.drawable.weixin);
        cm_menu.setSubIconResources(iconResArray);

        cm_menu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int index) {
                ToastUtil.showShortToast("点击了" + index + "这个位置");

            }
        });
        cm_menu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

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
            case R.id.btn_animator:
                startActivity(new Intent(getActivity(), AnimatorActivity.class));
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
