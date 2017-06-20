package com.example.vita.drawable;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vita on 17/6/20.
 */

public class ViewGroupFlow extends ViewGroup {
    private int childrenNum;
    private  View cView;
    private  MarginLayoutParams childrenParams=null;
    private  int[] changeline;
    private  int j = 1;



    public ViewGroupFlow (Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public ViewGroupFlow (Context context){
        super(context);
    }
    public ViewGroupFlow(Context context,AttributeSet attrs,int defstyleAttrs){
        super(context,attrs,defstyleAttrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizewidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeheight = MeasureSpec.getSize(heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        childrenNum = getChildCount();

        int sumWidth = 0;
        int sunWidthTest = 0;
        int sumHeight = 0;
        int cHeight;

        changeline[0]=0;

        for (int i=0;i<childrenNum;i++){
            cView=getChildAt(i);
            childrenParams =(MarginLayoutParams)cView.getLayoutParams();

            int childMargin = childrenParams.leftMargin+childrenParams.rightMargin;
            int cWidth = cView.getWidth()+childMargin;
            cHeight = cView.getHeight()+childrenParams.topMargin+ childrenParams.bottomMargin;

            sunWidthTest = sumWidth +cWidth;

            if (sunWidthTest<sizewidth){
                sumWidth = sunWidthTest;
                Log.i("######"+i,"     SumWidth"+sumWidth);
            }
            else if (sunWidthTest==sizewidth){
                width = sizewidth;
                Log.i("sunWidthTest==sizewidth"+i,"  SumWidth"+sumWidth);
                i=childrenNum;
            }
                else{
                width =Math.max(width,sumWidth);
                sumWidth = 0;
                height += cHeight;
                changeline[j]=i;//但是当前childview已经不能放在本行了，要放进下一行。
                j++;
                Log.i("本行最后一个第"+i+"个","     width"+width);
                Log.i("本行最后一个第"+i+"个","     SumHeight"+sumHeight);
                i--;
            }
        }

        width =Math.max(width,sumWidth);
        width = Math.min(width,sizewidth);
        height = height>sizeheight?sizeheight:height;

        setMeasuredDimension(widthmode==MeasureSpec.AT_MOST?width:sizewidth,heightmode==MeasureSpec.AT_MOST?height:sizeheight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int li = 1;li< j;li++){
            int num = changeline[li]-changeline[li-1];
            for (int i=0;i<)
        }

    }
}
