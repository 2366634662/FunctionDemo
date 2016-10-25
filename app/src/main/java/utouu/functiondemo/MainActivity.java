package utouu.functiondemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import utouu.functiondemo.adapter.MyFragmentPagerAdapter;
import utouu.functiondemo.base.BaseActivity;
import utouu.functiondemo.bean.TabEntity;
import utouu.functiondemo.fragment.ContactFragment;
import utouu.functiondemo.fragment.HomeFragment;
import utouu.functiondemo.fragment.MessageFragment;
import utouu.functiondemo.fragment.MineFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ctlayout_main_choice)
    CommonTabLayout ctlayoutMainChoice;
    @BindView(R.id.vp_main_page)
    ViewPager vpMainPage;
    private String[] mTitles = {"首页", "消息", "联系人", "我的"};
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

    @Override
    protected void initDatas() {
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mFragments.add(new HomeFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new ContactFragment());
        mFragments.add(new MineFragment());
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


}
