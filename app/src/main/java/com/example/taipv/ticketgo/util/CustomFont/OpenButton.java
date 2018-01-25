package com.example.taipv.ticketgo.util.CustomFont;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/25/2018
 * Email: tai97nd@gmail.com
 */

public class OpenButton extends AppCompatButton {

    public boolean mFixWindowWordEnabled = false;

    public OpenButton(Context context) {
        super(context);
        TypefaceCache.setCustomTypeface(context, this, null);
    }

    public OpenButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypefaceCache.setCustomTypeface(context, this, attrs);
    }

    public OpenButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypefaceCache.setCustomTypeface(context, this, attrs);
    }
}
