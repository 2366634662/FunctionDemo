package utouu.functiondemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseFragment;
import utouu.functiondemo.moudle.main.reciver.BoradCastReciverActivity;
import utouu.functiondemo.moudle.main.reciver.CallBackServiceActivity;

public class PageOneFragment extends BaseFragment {

    @BindView(R.id.btn_01)
    Button btn01;
    @BindView(R.id.btn_02)
    Button btn02;
    @BindView(R.id.btn_03)
    Button btn03;
    @BindView(R.id.btn_04)
    Button btn04;
    @BindView(R.id.btn_05)
    Button btn05;
    @BindView(R.id.btn_06)
    Button btn06;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {

    }


    @OnClick({R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05, R.id.btn_06})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_01:
                startActivity(new Intent(getActivity(), BoradCastReciverActivity.class));
                break;
            case R.id.btn_02:
                startActivity(new Intent(getActivity(), CallBackServiceActivity.class));
                break;
            case R.id.btn_03:
                break;
            case R.id.btn_04:
                break;
            case R.id.btn_05:
                break;
            case R.id.btn_06:
                break;
        }
    }
}
