package com.example.taipv.ticketgo.util.CustomFont;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/25/2018
 * Email: tai97nd@gmail.com
 */

public class OpenEditText extends AppCompatEditText {
    public OpenEditText(Context context) {
        super(context);
        TypefaceCache.setCustomTypeface(context, this, null);
    }

    public OpenEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypefaceCache.setCustomTypeface(context, this, attrs);
    }

    public OpenEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypefaceCache.setCustomTypeface(context, this, attrs);
    }
}

