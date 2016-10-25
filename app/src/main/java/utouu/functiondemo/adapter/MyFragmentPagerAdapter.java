package utouu.functiondemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Du_Li on 2016/10/25.
 * Desc:
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private ArrayList<Fragment> mFragments;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setmFragments(ArrayList<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments == null ? null : mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }
}
