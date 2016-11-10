package utouu.functiondemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseFragment;
import utouu.functiondemo.moudle.main.other.TabLayoutAndViewPagerActivity;

public class PageThreeFragment extends BaseFragment {


    @BindView(R.id.btn_totabactivity)
    Button btnTotabactivity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
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

    @Override
    @OnClick(R.id.btn_totabactivity)
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_totabactivity:
                startActivity(new Intent(getActivity(), TabLayoutAndViewPagerActivity.class));
                break;
        }


    }


}
