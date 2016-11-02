package utouu.functiondemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Du_Li on 2016/11/2.
 * Desc:
 */

public class MyElongVerticalTextView extends TextView {

    public MyElongVerticalTextView(Context context) {
        super(context);
    }

    public MyElongVerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyElongVerticalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        String verticalText = generateVerticalText(text.toString());
        super.setText(verticalText, type);
    }

    private String generateVerticalText(String desc) {
        StringBuilder stringBuffer = new StringBuilder();
        char[] chars = desc.toCharArray();
        for (char currentChar : chars) {
            String temp = currentChar + "\n";
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}