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
    private float circleX ;
    private float circleY ;
    private float circleR ;
    private int BallColor ;


    public DrawView(Context context, AttributeSet attr){
        super(context,attr);
        TypedArray a = context.obtainStyledAttributes(attr,R.styleable.DrawView);
        circleX = a.getFloat(R.styleable.DrawView_BallStartX,10);
        circleY = a.getFloat(R.styleable.DrawView_BallStartY,10);//设置默认值
        circleR = a.getFloat(R.styleable.DrawView_BallRadius,10);
        BallColor = a.getColor(R.styleable.DrawView_BallColor, 0x990000FF);
        a.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(BallColor);
        canvas.drawCircle(circleX,circleY,circleR,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        circleX = event.getX();
        circleY = event.getY();

        this.invalidate();
        return true;
    }

    public float getCircleR() {
        return circleR;
    }
    public float getCircleX(){
        return  circleX;
    }

    public float getCircleY() {
        return circleY;
    }

    public void setCircleR(float circleR) {
        this.circleR = circleR;
    }

    public void setCircleX(float circleX) {
        this.circleX = circleX;
    }

    public void setCircleY(float circleY) {
        this.circleY = circleY;
    }

}
