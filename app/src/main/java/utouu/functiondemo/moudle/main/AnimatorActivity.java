package utouu.functiondemo.moudle.main;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.util.SnackBarUtils;
import utouu.functiondemo.view.CircleMenu;

import static utouu.functiondemo.R.id.iv_1;
import static utouu.functiondemo.R.id.iv_iv1;

public class AnimatorActivity extends BaseActivity {

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
    @BindView(R.id.btn_07)
    Button btn07;
    @BindView(R.id.btn_08)
    Button btn08;
    @BindView(R.id.btn_09)
    Button btn09;
    @BindView(R.id.btn_10)
    Button btn10;
    @BindView(R.id.btn_11)
    Button btn11;
    @BindView(R.id.iv_iv2)
    ImageView ivIv2;
    @BindView(R.id.iv_iv3)
    ImageView ivIv3;
    @BindView(R.id.iv_iv4)
    ImageView ivIv4;
    @BindView(R.id.iv_iv5)
    ImageView ivIv5;
    @BindView(R.id.iv_iv6)
    ImageView ivIv6;
    @BindView(iv_iv1)
    ImageView ivIv1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.iv_6)
    ImageView iv6;
    @BindView(R.id.iv_7)
    ImageView iv7;
    @BindView(iv_1)
    ImageView iv1;
    private ImageView iv_ani_test;


    private List<ImageView> imageViews2 = new ArrayList<>();
    private List<ImageView> imageViews1 = new ArrayList<>();
    private CircleMenu circleMenu;

    @Override
    protected int getLayout() {
        return R.layout.activity_animator;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        iv_ani_test = findView(R.id.iv_ani_test);
        imageViews1.add(ivIv2);
        imageViews1.add(ivIv3);
        imageViews1.add(ivIv4);
        imageViews1.add(ivIv5);
        imageViews1.add(ivIv6);

        imageViews2.add(iv2);
        imageViews2.add(iv3);
        imageViews2.add(iv4);
        imageViews2.add(iv5);
        imageViews2.add(iv6);
        imageViews2.add(iv7);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.iv_iv1, R.id.iv_1, R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05, R.id.btn_06, R.id.btn_07, R.id.btn_08, R.id.btn_09, R.id.btn_10, R.id.btn_11})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_01://透明
                ObjectAnimator animator = ObjectAnimator.ofFloat(iv_ani_test, "alpha", 0f, 1f);
                animator.setDuration(1000);
                animator.start();
                break;
            case R.id.btn_02://缩放
                /**动画组合**/
                PropertyValuesHolder objectAnimatorScaleX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
                PropertyValuesHolder objectAnimatorScaleY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
                /**同时播放两个动画**/
                ObjectAnimator.ofPropertyValuesHolder(iv_ani_test, objectAnimatorScaleX, objectAnimatorScaleY).setDuration(1000).start();
                break;
            case R.id.btn_03://旋转
                ObjectAnimator objectAnimatorScale = ObjectAnimator.ofFloat(iv_ani_test, "rotation", 0f, 360f);
                objectAnimatorScale.setDuration(1000);
                objectAnimatorScale.start();
                break;
            case R.id.btn_04://平移
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv_ani_test, "translationX", 0f, 500f, 0f);
                animator1.setDuration(3000);
                animator1.start();
                break;
            case R.id.btn_05:// 缩放  旋转
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator objectAnimatorScaleX1 = ObjectAnimator.ofFloat(iv_ani_test, "scaleX", 0f, 1f);
                ObjectAnimator objectAnimatorScaleY1 = ObjectAnimator.ofFloat(iv_ani_test, "scaleY", 0f, 1f);
                ObjectAnimator objectAnimatorRotateX1 = ObjectAnimator.ofFloat(iv_ani_test, "rotationX", 0f, 360f);
                ObjectAnimator objectAnimatorRotateY1 = ObjectAnimator.ofFloat(iv_ani_test, "rotationY", 0f, 360f);
                animatorSet.setDuration(1000);
                animatorSet.play(objectAnimatorScaleX1).with(objectAnimatorScaleY1)
                        .before(objectAnimatorRotateX1).before(objectAnimatorRotateY1);
                animatorSet.start();
                break;
            case R.id.btn_06://先旋转  再位移
                AnimatorSet animatorSetGroup2 = new AnimatorSet();
                ObjectAnimator objectAnimatorTranslate2 = ObjectAnimator.ofFloat(iv_ani_test, "translationX", 0f, 500f);
                ObjectAnimator objectAnimatorRotateX2 = ObjectAnimator.ofFloat(iv_ani_test, "rotationX", 0f, 360f);
                ObjectAnimator objectAnimatorRotateY2 = ObjectAnimator.ofFloat(iv_ani_test, "rotationY", 0f, 360f);
                animatorSetGroup2.setDuration(1000);
                animatorSetGroup2.play(objectAnimatorTranslate2).after(objectAnimatorRotateX2)
                        .after(objectAnimatorRotateY2);
                animatorSetGroup2.start();
                break;

            case R.id.btn_07://重复动画   闪烁
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(iv_ani_test, "alpha", 0f, 1f);
                objectAnimator2.setDuration(500);
                objectAnimator2.setRepeatCount(Integer.MAX_VALUE);
                objectAnimator2.start();
                break;
            case R.id.btn_08:
                ObjectAnimator objectAnimatorTranslate3 = ObjectAnimator.ofFloat(iv_ani_test, "translationX", -25f, 50f);
                objectAnimatorTranslate3.setDuration(500);
                objectAnimatorTranslate3.setRepeatCount(Integer.MAX_VALUE);
                objectAnimatorTranslate3.start();
                break;
            case R.id.btn_09:
                ObjectAnimator objectAnimatorBg = ObjectAnimator.ofInt(iv_ani_test, "backgroundColor", Color.BLUE, Color.YELLOW, Color.RED);
                objectAnimatorBg.setDuration(3000);
                objectAnimatorBg.start();
                break;
            case R.id.btn_10:

                SnackBarUtils.showLong(iv_ani_test, "SnackBar").setAction("点击", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(AnimatorActivity.this, RegisterActivity.class));
                    }
                }).above(iv_ani_test, 30, 100, 100).messageCenter()
                        .show();
                break;
            case R.id.btn_11:

                break;
            /**
             * 圆形菜单
             */
            case R.id.iv_iv1:
                circleMenu = new CircleMenu(imageViews1);
                Boolean isShowing = (Boolean) ivIv1.getTag();
                if (null == isShowing || isShowing == false) {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivIv1, "rotation", 0, 45);
                    objectAnimator.setDuration(500);
                    objectAnimator.start();
                    ivIv1.setTag(true);
                    circleMenu.showCircleMenu();
                } else {
                    ivIv1.setTag(false);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivIv1, "rotation", 45, 0);
                    objectAnimator.setDuration(500);
                    objectAnimator.start();
                    circleMenu.closeCircleMenu();
                }
                break;
            /**
             * 扇形菜单
             */
            case R.id.iv_1:
                circleMenu = new CircleMenu(imageViews2);
                Boolean isShowing1 = (Boolean) iv1.getTag();
                if (null == isShowing1 || isShowing1 == false) {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv1, "rotation", 0, 45);
                    objectAnimator.setDuration(500);
                    objectAnimator.start();
                    iv1.setTag(true);
                    circleMenu.showSectorMenu();
                } else {
                    iv1.setTag(false);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv1, "rotation", 45, 0);
                    objectAnimator.setDuration(500);
                    objectAnimator.start();
                    circleMenu.closeSectorMenu();
                }
                break;
        }
    }
}
