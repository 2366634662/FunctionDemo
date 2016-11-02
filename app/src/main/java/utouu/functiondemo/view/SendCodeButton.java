package utouu.functiondemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;

import utouu.functiondemo.R;


/**
 * Created by Du_Li on 2016/11/1.
 * Desc:
 */

public class SendCodeButton extends Button implements Handler.Callback {

    //设置倒计时的时间
    private int totalTime;
    //记录倒计时的时间
    private int firstTime;
    //默认的时间
    private int conutTime = 60;
    //设置开始的时候的文字
    private String startText;
    //设置倒计时结束的文字
    private String endText;

    private Handler mHandler;

    public SendCodeButton(Context context) {
        this(context, null);
    }

    public SendCodeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SendCodeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CountDownButton, defStyleAttr, 0);
        totalTime = array.getInteger(R.styleable.CountDownButton_count_time, conutTime);
        startText = array.getString(R.styleable.CountDownButton_start_text) == null || "".equals(array.getString(R.styleable.CountDownButton_start_text)) ? "获取验证码" : array.getString(R.styleable.CountDownButton_start_text);
        endText = array.getString(R.styleable.CountDownButton_end_text) == null || "".equals(array.getString(R.styleable.CountDownButton_end_text)) ? "重新获取" : array.getString(R.styleable.CountDownButton_end_text);
        array.recycle();
        init(context);
    }

    private void init(Context context) {
        mHandler = new Handler(this);
        firstTime = totalTime;
        setText(startText);
    }

    public void start() {
        setClickable(false);
        mHandler.sendEmptyMessage(1);
    }

    public void stopConut() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public SendCodeButton setCountTime(int totalTime) {
        this.totalTime = totalTime;
        firstTime = totalTime;
        return this;
    }

    public SendCodeButton setStartText(String startText) {
        setText(startText);
        this.startText = startText;
        return this;
    }

    public SendCodeButton setEndText(String endText) {
        this.endText = endText;
        return this;
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case 1:
                totalTime--;
                if (totalTime == 0) {
                    setClickable(true);
                    totalTime = firstTime;
                    setText(endText);
                    mHandler.sendEmptyMessage(2);
                } else {
                    setText("" + totalTime + "S后重新获取");
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                }

                break;
            case 2:
                mHandler.removeCallbacksAndMessages(null);
                break;
        }
        return false;
    }
}
