package utouu.functiondemo.moudle.main.other;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import utouu.functiondemo.R;
import utouu.functiondemo.fragment.PageFourFragment;
import utouu.functiondemo.fragment.childfg.VideoFragment;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.base.BaseFragment;

public class TabLayoutAndViewPagerActivity extends BaseActivity {

    @BindView(R.id.tlayout_title)
    TabLayout tlayoutTitle;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private static final String TAB_NAME_1 = "流量图像";
    private static final String TAB_NAME_2 = "监控视频";
    private static final String TAB_NAME_3 = "个人中心";
    private List<String> tabNames;
    private List<BaseFragment> fragments;
    private List<Integer> tabicons;

    @Override
    protected int getLayout() {
        return R.layout.activity_tab_layout_and_view_pager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initDatas() {
        tabicons = new ArrayList<>();
        tabNames = new ArrayList<>();
        fragments = new ArrayList<>();
        tabNames.add(TAB_NAME_1);
        tabNames.add(TAB_NAME_2);
        tabNames.add(TAB_NAME_3);

        tabicons.add(R.mipmap.nav_icon_home_nor);
        tabicons.add(R.mipmap.nav_icon_home_nor);
        tabicons.add(R.mipmap.nav_icon_home_nor);


        fragments.add(new VideoFragment());
        fragments.add(new PageFourFragment());
        fragments.add(new VideoFragment());

        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                if (tabNames == null) {
                    spannableString = new SpannableString(" ");
                } else {
                    spannableString = new SpannableString(" " + tabNames.get(position));
                }
                if (tabicons != null) {
                    spannableString.setSpan(new ImageSpan(TabLayoutAndViewPagerActivity.this, tabicons.get(position)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                return spannableString;
            }
        });
        tlayoutTitle.setupWithViewPager(vpContent);
    }

    private SpannableString spannableString;

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {

    }

}
