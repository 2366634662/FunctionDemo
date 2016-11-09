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

import butterknife.BindView;
import butterknife.OnClick;
import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseActivity;
import utouu.functiondemo.framework.util.SnackBarUtils;

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
    private ImageView iv_ani_test;


    @Override
    protected int getLayout() {
        return R.layout.activity_animator;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        iv_ani_test = findView(R.id.iv_ani_test);

    }

    @Override
    protected void initDatas() {


    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05, R.id.btn_06, R.id.btn_07, R.id.btn_08, R.id.btn_09, R.id.btn_10, R.id.btn_11})
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
        }
    }
}
