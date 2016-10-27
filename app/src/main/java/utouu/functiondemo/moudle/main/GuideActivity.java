package utouu.functiondemo.moudle.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import utouu.functiondemo.R;
import utouu.functiondemo.bgabanner.BGABanner;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.sharedpreferences.SharePrefreceHelper;

/**
 * 引导页面
 */
public class GuideActivity extends BaseActivity {


    @BindView(R.id.bga_guide_bg)
    BGABanner bgaGuideBg;
    @BindView(R.id.btn_guide_getin)
    Button btnGuideGetin;

    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bgaGuideBg.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);

            }
        });
        bgaGuideBg.setData(Arrays.asList(R.mipmap.guide_01, R.mipmap.guide_03, R.mipmap.guide_04), null);

    }

    @Override
    protected void initDatas() {
        bgaGuideBg.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2)
                    btnGuideGetin.setVisibility(View.VISIBLE);
                else
                    btnGuideGetin.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    @OnClick(R.id.btn_guide_getin)
    public void onClick(View v) {
        SharePrefreceHelper.getInstence(this).setIsFirstIn(false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
