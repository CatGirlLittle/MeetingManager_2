package com.dongdao.meetingmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/18.
 * 自定义TextView用来实现跑马灯
 */
public class MtTextView extends TextView {
    public MtTextView(Context context) {
        super(context);
    }

    public MtTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MtTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
