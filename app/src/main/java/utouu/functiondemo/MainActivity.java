package utouu.functiondemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import utouu.functiondemo.adapter.MyFragmentPagerAdapter;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.bean.TabEntity;
import utouu.functiondemo.fragment.PageThreeFragment;
import utouu.functiondemo.fragment.PageFourFragment;
import utouu.functiondemo.fragment.PageOneFragment;
import utouu.functiondemo.fragment.PageTwoFragment;
import utouu.functiondemo.view.MyViewPager;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.ctlayout_main_choice)
    CommonTabLayout ctlayoutMainChoice;
    @BindView(R.id.vp_main_page)
    MyViewPager vpMainPage;//页面
    private TextView textView;
    private String[] mTitles = {"页面一", "页面二", "页面三", "页面四"};
    private int[] mIconUnselectIds = {
            R.mipmap.nav_icon_home_nor, R.mipmap.nav_icon_order_nor,
            R.mipmap.nav_icon_server_nor, R.mipmap.nav_icon_my_nor};
    private int[] mIconSelectIds = {
            R.mipmap.nav_icon_home_sel, R.mipmap.nav_icon_order_sel,
            R.mipmap.nav_icon_server_sel, R.mipmap.nav_icon_my_sel};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyFragmentPagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    /**
     * gaidong l打卡上课了；剪短发开了就上课了大家卡死
     */
    @Override
    protected void initDatas() {
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mFragments.add(new PageOneFragment());
        mFragments.add(new PageTwoFragment());
        mFragments.add(new PageThreeFragment());
        mFragments.add(new PageFourFragment());
        adapter.setmFragments(mFragments);
        vpMainPage.setAdapter(adapter);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        ctlayoutMainChoice.setTabData(mTabEntities);

        ctlayoutMainChoice.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMainPage.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vpMainPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ctlayoutMainChoice.setCurrentTab(position);
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
    public void onClick(View v) {

    }
}
