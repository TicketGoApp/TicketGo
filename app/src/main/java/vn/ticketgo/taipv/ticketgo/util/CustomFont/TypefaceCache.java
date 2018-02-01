package vn.ticketgo.taipv.ticketgo.util.CustomFont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * @Author:HoaLQ History:1/21/16.
 */
public class TypefaceCache {

    public static final int SEN_BOLD = 0;
    public static final int SEND_EXTRABOLD = 1;
    public static final int SEND_REGULAR = 2;

    protected static final Hashtable<String, Typeface> mTypefaceCache = new Hashtable<>();

    public static Typeface getTypeface(Context context) {
        return getTypeface(context, Typeface.NORMAL, SEN_BOLD);
    }

    public static Typeface getTypeface(Context context, int fontStyle, int variation) {
        if (context == null) {
            return null;
        }

        String typefaceName = "sen-bold.otf";
        if (variation == SEND_EXTRABOLD) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "sen-extrabold.otf";
                    break;
                }
                default: {
                    typefaceName = "sen-bold.otf";
                    break;
                }
            }
        } else if (variation == SEND_REGULAR) {
            switch (fontStyle) {
                case Typeface.ITALIC: {
                    typefaceName = "sen-regular.otf";
                    break;
                }
                default: {
                    typefaceName = "sen-bold.otf";
                    break;
                }
            }
        }

        return getTypefaceForTypefaceName(context, typefaceName);

    }

    protected static Typeface getTypefaceForTypefaceName(Context context, String typefaceName) {
        if (!mTypefaceCache.containsKey(typefaceName)) {
            Typeface typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/" + typefaceName);
            if (typeface != null) {
                mTypefaceCache.put(typefaceName, typeface);
            }
        }
        return mTypefaceCache.get(typefaceName);
    }


    public static void setCustomTypeface(Context context, TextView view, AttributeSet attributeSet) {
        if (context == null || view == null) {
            return;
        }

        // Skip at design-time (edit mode)
        if (view.isInEditMode()) {
            return;
        }

        // read custom fontVariation from attributes , default to normal
        int variation = TypefaceCache.SEND_REGULAR;
        if (attributeSet != null) {
            TypedArray array = context.getTheme().obtainStyledAttributes(attributeSet, vn.ticketgo.taipv.ticketgo.R.styleable.OpenTextView, 0, 0);
            if (array != null) {
                try {
                    variation = array.getInteger(vn.ticketgo.taipv.ticketgo.R.styleable.OpenTextView_fontVariation, TypefaceCache.SEN_BOLD);
                } finally {
                    array.recycle();
                }
            }
        }

        final int fontStype;
        if (view.getTypeface() != null) {
            boolean isBold = view.getTypeface().isBold();
            boolean isItalic = view.getTypeface().isItalic();
            if (isBold && isItalic) {
                fontStype = Typeface.BOLD_ITALIC;
            } else if (isBold) {
                fontStype = Typeface.BOLD;
            } else if (isItalic) {
                fontStype = Typeface.ITALIC;
            } else {
                fontStype = Typeface.NORMAL;
            }
        } else {
            fontStype = Typeface.NORMAL;
        }

        Typeface typeface = getTypeface(context, fontStype, variation);
        if (typeface != null) {
            view.setTypeface(typeface);
        }
    }
}

