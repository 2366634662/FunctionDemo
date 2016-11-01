package utouu.functiondemo.moudle.main.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.moudle.main.LoginActivity;

public class MyGuideActivity extends BaseActivity {

    private ViewPager vp_myguide_bg;

    private ImageView[] imageViews = new ImageView[3];

    private Integer[] img_ids = {R.id.iv_icon1, R.id.iv_icon2, R.id.iv_icon3};

    private Integer[] img_vg = {R.mipmap.guide_01, R.mipmap.guide_03, R.mipmap.guide_04};

    private Button btn_myguide_getin;

    private LinearLayout llayout_point;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_guide;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        vp_myguide_bg = findView(R.id.vp_myguide_bg);
        btn_myguide_getin = findView(R.id.btn_myguide_getin);

        llayout_point = findView(R.id.llayout_point);

        setOnClick(btn_myguide_getin);

        for (int i = 0; i < imageViews.length; i++) {
//            imageViews[i] = findView(img_ids[i]);
            //另外种找ID 的方式   通过getChildAt（i） 可以拿到这个线性布局包含的所有的控件
            imageViews[i] = (ImageView) llayout_point.getChildAt(i);
        }
    }

    @Override
    protected void initDatas() {
        vp_myguide_bg.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return img_vg.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MyGuideActivity.this);
                imageView.setBackgroundResource(img_vg[position]);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((ImageView) object);
            }
        });

        vp_myguide_bg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == (imageViews.length - 1)) {
                    btn_myguide_getin.setVisibility(View.VISIBLE);
                } else
                    btn_myguide_getin.setVisibility(View.GONE);

                for (int i = 0; i < imageViews.length; i++) {
                    if (i == position) {
                        imageViews[i].setBackgroundResource(R.drawable.adv_gallery_mark);
                    } else {
                        imageViews[i].setBackgroundResource(R.drawable.adv_gallery_unmark);
                    }
                }

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

        startActivity(new Intent(this, LoginActivity.class));

    }
}
