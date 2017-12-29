package com.example.taipv.ticketgo.view.activity.CodeQR;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
//Giu Tam Sang De Code
/**
 * Created by taipv on 12/27/2017.
 */

public class TicketView extends LinearLayout {
    private Paint eraser;
    private Bitmap bitmap;
    private Canvas cv;
    private int holesBottomMargin = 70;
    private int holeRadius = 40;

    public TicketView(Context context) {
        super(context);
        Init();
    }


    public TicketView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init();

    }

    public TicketView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();

    }

    private void Init() {
        eraser = new Paint();
        //Set or clear the xfermode object. Pass null to clear any previous xfermode. As a convenience, the parameter passed is also returned.
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        //Helper for setFlags(), setting or clearing the ANTI_ALIAS_FLAG bit AntiAliasing smooths out the edges of what is being drawn, but is has no impact on the interior of the shape. See setDither() and setFilterBitmap() to affect how colors are treated.
        eraser.setAntiAlias(true);

    }

    @Override
    //Returns the position in the group of the specified child view.
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w!=oldw || h !=oldh)
            bitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        cv=new Canvas(bitmap);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w=getWidth();
        int h=getHeight();
        bitmap.eraseColor(Color.TRANSPARENT);

        //set the view bakground color
        cv.drawColor(Color.WHITE);

        //drawing footer square contains the buy now buttno
        Paint paint=new Paint();
        paint.setARGB(250,250,250,250);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);

        //Draw the specified Rect using the specified paint. The rectangle will be filled or framed based on the Style in the paint.
        cv.drawRect(0,h,w, h - pxFromDp(getContext(),holesBottomMargin),paint);

        //adding punching holdes on the ticket by erasing them
        cv.drawCircle(0,0,holeRadius,eraser);//top -left hole
        cv.drawCircle(w/2,0,holeRadius,eraser);//top-middle hole left
        cv.drawCircle(0,h-pxFromDp(getContext(),holesBottomMargin),holeRadius,eraser);//bot - left
        cv.drawCircle(w,h - pxFromDp(getContext(),holesBottomMargin),holeRadius,eraser);//bot right

        //drawing the image
        canvas.drawBitmap(bitmap,0,0,null);

        //drawing dashed lines at the bottom
        Path path=new Path();
        path.moveTo(holeRadius,h-pxFromDp(getContext(),holesBottomMargin));
        path.quadTo(w-holeRadius,h - pxFromDp(getContext(),holesBottomMargin),w-holeRadius,h-pxFromDp(getContext(),holesBottomMargin));

        //dashed line
        Paint dashed =new Paint();
        dashed.setARGB(255,200,200,200);
        dashed.setStyle(Paint.Style.STROKE);
        dashed.setStrokeWidth(2);
        dashed.setPathEffect(new DashPathEffect(new float[]{10,5},0));

        super.onDraw(canvas);

    }
    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
