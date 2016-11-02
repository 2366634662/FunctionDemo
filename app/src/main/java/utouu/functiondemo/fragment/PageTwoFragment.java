package utouu.functiondemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import utouu.functiondemo.R;
import utouu.functiondemo.adapter.MyFragmentPagerAdapter;
import utouu.functiondemo.framework.base.BaseFragment;
import utouu.functiondemo.bean.TabEntity;
import utouu.functiondemo.fragment.childfg.LiveFragment;
import utouu.functiondemo.fragment.childfg.MusicFragment;
import utouu.functiondemo.fragment.childfg.VideoFragment;
import utouu.functiondemo.view.MyViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageTwoFragment extends BaseFragment {

    @BindView(R.id.ctlayout_title)
    CommonTabLayout ctlayoutTitle;
    @BindView(R.id.vp_fragment_page)
    MyViewPager vpFragmentPage;
    private String[] mTitles = {"视频播放", "音乐播放", "直播"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragment = new ArrayList<>();

    private MyFragmentPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        adapter = new MyFragmentPagerAdapter(getFragmentManager());

        mFragment.add(new VideoFragment());
        mFragment.add(new MusicFragment());
        mFragment.add(new LiveFragment());
        adapter.setmFragments(mFragment);
        vpFragmentPage.setAdapter(adapter);

        ctlayoutTitle.setTabData(mTabEntities);
        ctlayoutTitle.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpFragmentPage.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
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
