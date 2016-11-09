package utouu.functiondemo.framework.util;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import utouu.functiondemo.R;


/**
 * Created by Du_Li on 2016/11/9.
 * Desc:
 */

public class SnackBarUtils {
    //设置Snackbar背景颜色
    private static final int color_info = 0XFF2094F3;
    private static final int color_confirm = 0XFF4CB04E;
    private static final int color_warning = 0XFFFEC005;
    private static final int color_danger = 0XFFF44336;
    //工具类当前持有的Snackbar实例
    private static WeakReference<Snackbar> snackbarWeakReference;

    private SnackBarUtils() {
        throw new RuntimeException("禁止无参实例化");
    }

    public SnackBarUtils(@Nullable WeakReference<Snackbar> snackbarWeakReference) {
        this.snackbarWeakReference = snackbarWeakReference;
    }

    /**
     * 获取 Snackbar
     *
     * @return
     */
    public Snackbar getSnackBar() {
        if (snackbarWeakReference != null && snackbarWeakReference.get() != null) {
            return snackbarWeakReference.get();
        } else {
            return null;
        }
    }

    /**
     * 短时间显示SnackBar
     *
     * @param view
     * @param msg
     * @return
     */
    public static SnackBarUtils showShort(View view, String msg) {


        return new SnackBarUtils(new WeakReference<Snackbar>(Snackbar.make(view, msg, Snackbar.LENGTH_SHORT))).backColor(0XFF323232);
    }

    /**
     * 长时间显示SnackBar
     *
     * @param view
     * @param msg
     * @return
     */
    public static SnackBarUtils showLong(View view, String msg) {
        return new SnackBarUtils(new WeakReference<Snackbar>(Snackbar.make(view, msg, Snackbar.LENGTH_LONG))).backColor(0XFF323232);
    }

    public static SnackBarUtils showIndefinite(View view, String msg) {
        return new SnackBarUtils(new WeakReference<Snackbar>(Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE))).backColor(0XFF323232);
    }

    /**
     * 设置SnackBar背景颜色
     *
     * @return
     */
    public SnackBarUtils setBackGroundColor() {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            snackbar.getView().setBackgroundColor(color_info);
        }
        return new SnackBarUtils(snackbarWeakReference);

    }

    /**
     * set content caolor
     *
     * @param color
     * @return
     */
    public SnackBarUtils setMessageColor(int color) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            ((TextView) (snackbar.getView().findViewById(android.support.design.R.id.snackbar_text))).setTextColor(color);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * s设置按钮里面文字的颜色
     *
     * @param color
     * @return
     */
    public SnackBarUtils setButtonColor(int color) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {

            Button action;

            action = (Button) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);

            action.setTextColor(color);

        }

        return new SnackBarUtils(snackbarWeakReference);

    }

    /**
     * @param backColor   SnackBar 背景颜色
     * @param msgColor    SnackBar 要显示的内容的文字的颜色
     * @param actionColor SnackBar 右边按钮的文字的颜色
     * @return
     */
    public SnackBarUtils setAllColor(int backColor, int msgColor, int actionColor) {
        Snackbar snackbar = getSnackBar();
        TextView tv_snack;
        Button btn_snack;
        if (snackbar != null) {
            snackbar.getView().setBackgroundColor(backColor);
            tv_snack = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            btn_snack = (Button) snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);
            tv_snack.setTextColor(msgColor);
            btn_snack.setTextColor(actionColor);
        }

        return new SnackBarUtils(snackbarWeakReference);

    }

    /**
     * 设置透明度
     *
     * @param alpha 透明度  大于 0 小于 1
     * @return
     */
    public SnackBarUtils setAlpha(float alpha) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            alpha = alpha >= 1.0f ? 1.0f : (alpha <= 0.0f ? 0.0f : alpha);
            snackbar.getView().setAlpha(alpha);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置Snackbar显示的位置
     *
     * @param gravity
     */
    public SnackBarUtils gravityFrameLayout(int gravity) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(snackbarWeakReference.get().getView().getLayoutParams().width, snackbarWeakReference.get().getView().getLayoutParams().height);
            params.gravity = gravity;
            snackbar.getView().setLayoutParams(params);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置Snackbar显示的位置,当Snackbar和CoordinatorLayout组合使用的时候
     *
     * @param gravity
     */
    public SnackBarUtils gravityCoordinatorLayout(int gravity) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(snackbarWeakReference.get().getView().getLayoutParams().width, snackbarWeakReference.get().getView().getLayoutParams().height);
            params.gravity = gravity;
            snackbar.getView().setLayoutParams(params);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置按钮文字内容 及 点击监听
     * {@link Snackbar#setAction(CharSequence, View.OnClickListener)}
     *
     * @param resId
     * @param listener
     * @return
     */
    public SnackBarUtils setAction(@StringRes int resId, View.OnClickListener listener) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            return setAction(snackbar.getView().getResources().getText(resId), listener);
        } else {
            return new SnackBarUtils(snackbarWeakReference);
        }
    }

    /**
     * 设置按钮文字内容 及 点击监听
     * {@link Snackbar#setAction(CharSequence, View.OnClickListener)}
     *
     * @param text
     * @param listener
     * @return
     */
    public SnackBarUtils setAction(CharSequence text, View.OnClickListener listener) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            snackbar.setAction(text, listener);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置 mSnackbar 展示完成 及 隐藏完成 的监听
     *
     * @param setCallback
     * @return
     */
    public SnackBarUtils setCallback(Snackbar.Callback setCallback) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            snackbar.setCallback(setCallback);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }


    /**
     * 设置TextView(@+id/snackbar_text)左右两侧的图片
     *
     * @param leftDrawable
     * @param rightDrawable
     * @return
     */
    public SnackBarUtils leftAndRightDrawable(@Nullable @DrawableRes Integer leftDrawable, @Nullable @DrawableRes Integer rightDrawable) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            Drawable drawableLeft = null;
            Drawable drawableRight = null;
            if (leftDrawable != null) {
                try {
                    drawableLeft = snackbar.getView().getResources().getDrawable(leftDrawable.intValue());
                } catch (Exception e) {
                }
            }
            if (rightDrawable != null) {
                try {
                    drawableRight = snackbar.getView().getResources().getDrawable(rightDrawable.intValue());
                } catch (Exception e) {
                }
            }
            return leftAndRightDrawable(drawableLeft, drawableRight);
        } else {
            return new SnackBarUtils(snackbarWeakReference);
        }
    }

    /**
     * 设置TextView(@+id/snackbar_text)左右两侧的图片
     *
     * @param leftDrawable
     * @param rightDrawable
     * @return
     */
    public SnackBarUtils leftAndRightDrawable(@Nullable Drawable leftDrawable, @Nullable Drawable rightDrawable) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            TextView message = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
            LinearLayout.LayoutParams paramsMessage = (LinearLayout.LayoutParams) message.getLayoutParams();
            paramsMessage = new LinearLayout.LayoutParams(paramsMessage.width, paramsMessage.height, 0.0f);
            message.setLayoutParams(paramsMessage);
            message.setCompoundDrawablePadding(message.getPaddingLeft());
            int textSize = (int) message.getTextSize();
            if (leftDrawable != null) {
                leftDrawable.setBounds(0, 0, textSize, textSize);
            }
            if (rightDrawable != null) {
                rightDrawable.setBounds(0, 0, textSize, textSize);
            }
            message.setCompoundDrawables(leftDrawable, null, rightDrawable, null);
            LinearLayout.LayoutParams paramsSpace = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            ((Snackbar.SnackbarLayout) snackbar.getView()).addView(new Space(snackbar.getView().getContext()), 1, paramsSpace);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置TextView(@+id/snackbar_text)中文字的对齐方式 居中
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public SnackBarUtils messageCenter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Snackbar snackbar = getSnackBar();
            if (snackbar != null) {
                TextView message = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
                //View.setTextAlignment需要SDK>=17
                message.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
                message.setGravity(Gravity.CENTER);
            }
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置TextView(@+id/snackbar_text)中文字的对齐方式 居右
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public SnackBarUtils messageRight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Snackbar snackbar = getSnackBar();
            if (snackbar != null) {
                TextView message = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
                //View.setTextAlignment需要SDK>=17
                message.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
                message.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置Snackbar布局的外边距
     * 注:经试验发现,调用margins后再调用 gravityFrameLayout,则margins无效.
     * 为保证margins有效,应该先调用 gravityFrameLayout,在 show() 之前调用 margins
     *
     * @param margin
     * @return
     */
    public SnackBarUtils margins(int margin) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            return margins(margin, margin, margin, margin);
        } else {
            return new SnackBarUtils(snackbarWeakReference);
        }
    }

    /**
     * 设置Snackbar布局的外边距
     * 注:经试验发现,调用margins后再调用 gravityFrameLayout,则margins无效.
     * 为保证margins有效,应该先调用 gravityFrameLayout,在 show() 之前调用 margins
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public SnackBarUtils margins(int left, int top, int right, int bottom) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            ViewGroup.LayoutParams params = snackbar.getView().getLayoutParams();
            ((ViewGroup.MarginLayoutParams) params).setMargins(left, top, right, bottom);
            snackbar.getView().setLayoutParams(params);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 通过SnackBar现在的背景,获取其设置圆角值时候所需的GradientDrawable实例
     *
     * @param backgroundOri
     * @return
     */
    private GradientDrawable getRadiusDrawable(Drawable backgroundOri) {
        GradientDrawable background = null;
        if (backgroundOri instanceof GradientDrawable) {
            background = (GradientDrawable) backgroundOri;
        } else if (backgroundOri instanceof ColorDrawable) {
            int backgroundColor = ((ColorDrawable) backgroundOri).getColor();
            background = new GradientDrawable();
            background.setColor(backgroundColor);
        } else {
        }
        return background;
    }

    /**
     * 设置Snackbar布局的圆角半径值
     *
     * @param radius 圆角半径
     * @return
     */
    public SnackBarUtils radius(float radius) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            //将要设置给mSnackbar的背景
            GradientDrawable background = getRadiusDrawable(snackbar.getView().getBackground());
            if (background != null) {
                radius = radius <= 0 ? 12 : radius;
                background.setCornerRadius(radius);
                snackbar.getView().setBackgroundDrawable(background);
            }
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置Snackbar布局的圆角半径值及边框颜色及边框宽度
     *
     * @param radius
     * @param strokeWidth
     * @param strokeColor
     * @return
     */
    public SnackBarUtils radius(int radius, int strokeWidth, @ColorInt int strokeColor) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            //将要设置给mSnackbar的背景
            GradientDrawable background = getRadiusDrawable(snackbar.getView().getBackground());
            if (background != null) {
                radius = radius <= 0 ? 12 : radius;
                strokeWidth = strokeWidth <= 0 ? 1 : (strokeWidth >= snackbar.getView().findViewById(R.id.snackbar_text).getPaddingTop() ? 2 : strokeWidth);
                background.setCornerRadius(radius);
                background.setStroke(strokeWidth, strokeColor);
                snackbar.getView().setBackgroundDrawable(background);
            }
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 计算单行的Snackbar的高度值(单位 pix)
     *
     * @return
     */
    private int calculateSnackBarHeight() {
        //文字高度+paddingTop+paddingBottom : 14sp + 14dp*2
        int SnackbarHeight = ScreenUtil.dp2px(snackbarWeakReference.get().getView().getContext(), 28) + ScreenUtil.sp2px(snackbarWeakReference.get().getView().getContext(), 14);
        Log.e("tag", "直接获取MessageView高度:" + snackbarWeakReference.get().getView().findViewById(R.id.snackbar_text).getHeight());
        return SnackbarHeight;
    }

    /**
     * 设置Snackbar显示在指定View的上方
     * 注:暂时仅支持单行的Snackbar,因为{@link SnackBarUtils#calculateSnackBarHeight()}暂时仅支持单行Snackbar的高度计算
     *
     * @param targetView     指定View
     * @param contentViewTop Activity中的View布局区域 距离屏幕顶端的距离
     * @param marginLeft     左边距
     * @param marginRight    右边距
     * @return
     */
    public SnackBarUtils above(View targetView, int contentViewTop, int marginLeft, int marginRight) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            marginLeft = marginLeft <= 0 ? 0 : marginLeft;
            marginRight = marginRight <= 0 ? 0 : marginRight;
            int[] locations = new int[2];
            targetView.getLocationOnScreen(locations);
            int snackbarHeight = calculateSnackBarHeight();
            //必须保证指定View的顶部可见 且 单行Snackbar可以完整的展示
            if (locations[1] >= contentViewTop + snackbarHeight) {
                gravityFrameLayout(Gravity.BOTTOM);
                ViewGroup.LayoutParams params = snackbar.getView().getLayoutParams();
                ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, snackbar.getView().getResources().getDisplayMetrics().heightPixels - locations[1]);
                snackbar.getView().setLayoutParams(params);
            }
        }
        return new SnackBarUtils(snackbarWeakReference);
    }

    /**
     * 设置Snackbar显示在指定View的下方
     * 注:暂时仅支持单行的Snackbar,因为{@link SnackBarUtils#calculateSnackBarHeight()}暂时仅支持单行Snackbar的高度计算
     *
     * @param targetView     指定View
     * @param contentViewTop Activity中的View布局区域 距离屏幕顶端的距离
     * @param marginLeft     左边距
     * @param marginRight    右边距
     * @return
     */
    public SnackBarUtils below(View targetView, int contentViewTop, int marginLeft, int marginRight) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            marginLeft = marginLeft <= 0 ? 0 : marginLeft;
            marginRight = marginRight <= 0 ? 0 : marginRight;
            int[] locations = new int[2];
            targetView.getLocationOnScreen(locations);
            int snackbarHeight = calculateSnackBarHeight();
            int screenHeight = ScreenUtil.getScreenHeight(snackbar.getView().getContext());
            //必须保证指定View的底部可见 且 单行Snackbar可以完整的展示
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //为什么要'+2'? 因为在Android L(Build.VERSION_CODES.LOLLIPOP)以上,例如Button会有一定的'阴影(shadow)',阴影的大小由'高度(elevation)'决定.
                //为了在Android L以上的系统中展示的Snackbar不要覆盖targetView的阴影部分太大比例,所以人为减小2px的layout_marginBottom属性.
                if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight + 2 <= screenHeight) {
                    gravityFrameLayout(Gravity.BOTTOM);
                    ViewGroup.LayoutParams params = snackbar.getView().getLayoutParams();
                    ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight + 2));
                    snackbar.getView().setLayoutParams(params);
                }
            } else {
                if (locations[1] + targetView.getHeight() >= contentViewTop && locations[1] + targetView.getHeight() + snackbarHeight <= screenHeight) {
                    gravityFrameLayout(Gravity.BOTTOM);
                    ViewGroup.LayoutParams params = snackbar.getView().getLayoutParams();
                    ((ViewGroup.MarginLayoutParams) params).setMargins(marginLeft, 0, marginRight, screenHeight - (locations[1] + targetView.getHeight() + snackbarHeight));
                    snackbar.getView().setLayoutParams(params);
                }
            }
        }
        return new SnackBarUtils(snackbarWeakReference);
    }


    /**
     * 显示 mSnackbar
     */
    public void show() {
        if (getSnackBar() != null) {
            snackbarWeakReference.get().show();
        } else {
        }
    }

    /**
     * 设置Snackbar背景色
     *
     * @param backgroundColor
     */
    public SnackBarUtils backColor(@ColorInt int backgroundColor) {
        Snackbar snackbar = getSnackBar();
        if (snackbar != null) {
            snackbar.getView().setBackgroundColor(backgroundColor);
        }
        return new SnackBarUtils(snackbarWeakReference);
    }


}
