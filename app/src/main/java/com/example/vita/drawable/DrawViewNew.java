package com.example.vita.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Vita on 17/6/19.
 */

public class DrawViewNew extends View {

    private float mcircleRadious ;
    private float mStartX;
    private float mStartY;
    private int paintColor =Color.BLUE;
    private DrawViewNew mDrawViewNew;
    public DrawViewNew (Context context, AttributeSet attr){
        super(context,attr);
        TypedArray a = context.obtainStyledAttributes(attr, R.styleable.DrawViewNew);
        mcircleRadious = a.getFloat(R.styleable.DrawViewNew_CircleRadius,30);
        mStartX = a.getFloat(R.styleable.DrawViewNew_CircleStartX,10);
        mStartY = a.getFloat(R.styleable.DrawViewNew_CircleStartY,10);

        Log.e("tag", "DrawViewNew radius" + mcircleRadious);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("############","touch");

        setPaintColor(Color.RED);
        Log.e("############","paintcolor"+paintColor);
        setCircleRadious(60);
        Log.e("############","touch radious"+mcircleRadious);
        this.invalidate();
        return true;
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(circleRadious, circleRadious);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        paint.setColor(paintColor);
        Log.e("tag", "radius" + mcircleRadious);
        canvas.drawCircle(110,100,mcircleRadious,paint);
    }

    public void setCircleRadious(int circleRadious) {
        this.mcircleRadious = circleRadious;
    }

    public void setPaintColor(int Color) {
        this.paintColor = Color;
    }

}
