package utouu.functiondemo.fragment;

import android.os.Bundle;
import android.view.View;

import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseFragment;
import utouu.functiondemo.view.SendCodeButton;

public class PageFourFragment extends BaseFragment {

    private SendCodeButton scb_code;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        scb_code = findView(R.id.scb_code);
//        scb_code.setCountTime(20).setStartText("获取").setEndText("再来");
    }

    @Override
    protected void initData() {
        scb_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scb_code.start();
            }
        });
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {

    }


}
