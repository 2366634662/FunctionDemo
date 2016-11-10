package utouu.functiondemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Du_Li on 2016/11/10.
 * Desc:
 */

public class MyCircleView extends View {

    private Paint paint;

    private Context context;

    public MyCircleView(Context context) {
        super(context);
        this.context = context;
        init();

    }

    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);//消除锯齿
//        paint.setStyle(Paint.Style.STROKE);//绘制空心圆
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int center = getWidth() / 2;
        int innerCirlce1 = dip2px(context, 35);
        int innerCirlce = dip2px(context, 50);
        int ringWidth = dip2px(context, 50);

        paint.setARGB(255, 212, 225, 233);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(innerCirlce1);
        canvas.drawCircle(center, center, 45, paint);


        paint.setARGB(155, 167, 190, 206);
        paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCirlce, paint);

        paint.setARGB(255, 212, 225, 233);
        paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCirlce + 1 + ringWidth / 2, paint);

        paint.setARGB(155, 167, 190, 206);
        paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCirlce + ringWidth, paint);


        super.onDraw(canvas);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
