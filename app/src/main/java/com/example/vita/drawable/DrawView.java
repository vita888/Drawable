package com.example.vita.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.AttributedCharacterIterator;

/**
 * Created by Vita on 17/6/19.
 */

public class DrawView extends View {
    private float mCircleX ;
    private float mCircleY ;
    private float mCircleR ;
    private int mBallColor ;
    Paint mPaint = new Paint();


    public DrawView(Context context, AttributeSet attr){
        super(context,attr);
        TypedArray a = context.obtainStyledAttributes(attr,R.styleable.DrawView);//获取xml里的属性值
        mCircleX = a.getFloat(R.styleable.DrawView_BallStartX,10);
        mCircleY = a.getFloat(R.styleable.DrawView_BallStartY,10);//设置默认值
        mCircleR = a.getFloat(R.styleable.DrawView_BallRadius,10);
        mBallColor = a.getColor(R.styleable.DrawView_BallColor, 0x990000FF);
        a.recycle();//回收a。
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mBallColor);
            canvas.drawCircle(mCircleX,mCircleY,mCircleR,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mCircleX = event.getX();
        mCircleY = event.getY();

        this.invalidate();//刷新viewdc
        return true;
    }

    public float getCircleR() {
        return mCircleR;
    }
    public float getCircleX(){
        return  mCircleX;
    }

    public float getCircleY() {
        return mCircleY;
    }

    public void setCircleR(float circleR) {
        this.mCircleR = circleR;
    }

    public void setCircleX(float circleX) {
        this.mCircleX = circleX;
    }

    public void setCircleY(float circleY) {
        this.mCircleY = circleY;
    }

}
