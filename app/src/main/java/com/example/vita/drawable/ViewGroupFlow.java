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


    private int[] mChangeline;
    private int mLine;


    public ViewGroupFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupFlow(Context context) {
        super(context);
    }

    public ViewGroupFlow(Context context, AttributeSet attrs, int defstyleAttrs) {
        super(context, attrs, defstyleAttrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childrenNum = getChildCount();
        View cView;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        MarginLayoutParams childrenParams;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizewidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeheight = MeasureSpec.getSize(heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;


        mChangeline = new int[childrenNum + 1];
        int j = 1;

        int sumWidth = 0;
        int sunWidthTest = 0;
        int sumHeight = 0;
        int cHeight=0;

        mChangeline[0] = 0;

        for (int i = 0; i < childrenNum; i++) {
            cView = getChildAt(i);
            childrenParams = (MarginLayoutParams) cView.getLayoutParams();

            int childMargin = childrenParams.leftMargin + childrenParams.rightMargin;
            int cWidth = cView.getMeasuredWidth() + childMargin;
            cHeight = cView.getMeasuredHeight() + childrenParams.topMargin + childrenParams.bottomMargin;

            sunWidthTest = sumWidth + cWidth;

            Log.i("第" + i + "个的" + cView.getMeasuredWidth() + "__" + childMargin, "" + sunWidthTest + "与" + sizewidth);

            if (sunWidthTest < sizewidth) {
                sumWidth = sunWidthTest;
                Log.i("######" + i, "     SumWidth" + sumWidth);
            } else if (sunWidthTest == sizewidth) {
                width = sizewidth;
                Log.i("sunWidthTest==sizewidth" + i, "  SumWidth" + sumWidth);
                i = childrenNum;
            } else {
                width = Math.max(width, sumWidth);
                sumWidth = 0;
                height += cHeight;
                mChangeline[j] = i;//但是当前childview已经不能放在本行了，要放进下一行。
                j++;
                Log.i("本行最后一个第" + i + "个", "     width" + width);
                Log.i("本行最后一个第" + i + "个", "     SumHeight" + sumHeight);
                i--;
            }
        }

        width = Math.max(width, sumWidth);
        width = Math.min(width, sizewidth);
        height = height+ cHeight;
        height = height > sizeheight ? sizeheight : height;
        mLine = j;

        setMeasuredDimension(widthmode == MeasureSpec.AT_MOST ? width : sizewidth, heightmode == MeasureSpec.AT_MOST ? height : sizeheight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams childrenParams;

        View cView;
        int left = 0;
        int top = 0;
        int n = 0;
        mChangeline[mLine] = getChildCount();
        View cView1 = getChildAt(0);
        MarginLayoutParams childrenParams1 = (MarginLayoutParams) cView1.getLayoutParams();
        int lineHeight = childrenParams1.topMargin + cView1.getMeasuredHeight() + childrenParams1.bottomMargin;


        for (int li = 1; li <= mLine; li++) {//对每一行遍历
            int num = mChangeline[li] - mChangeline[li - 1];
            for (int i = 0; i < num; i++) {//对某行的每一个view遍历

                cView = getChildAt(n);
                childrenParams = (MarginLayoutParams) cView.getLayoutParams();
                l = left + childrenParams.leftMargin;
                t = top + childrenParams.topMargin;
                r = l + cView.getMeasuredWidth();
                b = t + cView.getMeasuredHeight();

                cView.layout(l, t, r, b);
                Log.i("layout参数是", "l=" + l + ".....t=" + t + ".....r=" + r + ".....b=" + b);
                left = r + childrenParams.rightMargin;
                n++;
            }
            left = 0;
            top += lineHeight;
        }

    }
}
