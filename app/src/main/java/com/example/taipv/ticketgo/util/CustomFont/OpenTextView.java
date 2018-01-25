package com.example.taipv.ticketgo.util.CustomFont;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/25/2018
 * Email: tai97nd@gmail.com
 */

public class OpenTextView extends AppCompatTextView {

//    private static final String TAG = LogUtils.makeLogTag(OpenTextView.class);

    public OpenTextView(Context context) {
        super(context);
        TypefaceCache.setCustomTypeface(context, this, null);
    }

    public OpenTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypefaceCache.setCustomTypeface(context, this, attrs);
    }

    public OpenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypefaceCache.setCustomTypeface(context, this, attrs);
    }
}
