package com.example.vita.drawable;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vita on 17/6/20.
 */

public class ViewGroupSquare extends ViewGroup {


    private int width = 0;
    private int height =0;
    private int childrenNum;
    private int childrenWidth =0;
    private int childrenHeight =0;
    private MarginLayoutParams childrenParam =null;
    private View childView;


//    public ViewGroup(Context context) {
//        this(context, null);
//    }
//
//    public ViewGroup(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public ViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
//        this(context, attrs, defStyleAttr, 0);
//    }
//
//    public ViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initViewGroup();
//        initFromAttributes(context, attrs, defStyleAttr, defStyleRes);
//    }
    public ViewGroupSquare(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    public ViewGroupSquare(Context context){
        super(context);
    }
    public ViewGroupSquare (Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
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
        int widthMode =  MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式 ,此时的viewgroup是父容器的view

        measureChildren(widthMeasureSpec,heightMeasureSpec);
         childrenNum = getChildCount();
//        int childrenWidth =0;
//        int childrenHeight =0;
//        MarginLayoutParams childrenParam =null;

        int leftHeight = 0;
        int rightHeight = 0;
        int topWidth = 0;
        int bottomWidth =0;

        for (int i = 0;i<childrenNum;i++){
            childView = getChildAt(i);
            childrenWidth =childView.getMeasuredWidth();
            childrenHeight = childView.getMeasuredHeight();
            childrenParam =(MarginLayoutParams)childView.getLayoutParams();

            if (i==0||i==1){
                topWidth += childrenWidth+childrenParam.leftMargin+childrenParam.rightMargin;
            }
            if (i==2||i==4){
                bottomWidth += childrenWidth+childrenParam.leftMargin+childrenParam.rightMargin;
            }
            if (i==0||i==2){
                leftHeight += childrenHeight+childrenParam.topMargin+childrenParam.bottomMargin;
            }
            if (i==1||i==3){
                rightHeight += childrenHeight+childrenParam.topMargin+childrenParam.bottomMargin;
            }

        }

        width = Math.max(topWidth,bottomWidth);
        Log.i("#######","width is"+width);
        height = Math.max(leftHeight,rightHeight);
        Log.i("#######","height is"+height);
        Log.i("#######","sizeheight is"+sizeheight);
        Log.i("#######","sizewidth is"+sizewidth);
        Log.i("#############",""+widthMode);
        Log.i("#####33333333",""+heightMode);

    setMeasuredDimension(widthMode==MeasureSpec.AT_MOST?width:sizewidth,heightMode==MeasureSpec.AT_MOST?height:sizeheight);//EXACTLY：表示设置了精确的值，一般当childView设置其宽、高为精确值、match_parent时，ViewGroup会将其设置为EXACTLY；
                                                                                                                            //  AT_MOST：表示子布局被限制在一个最大值内，一般当childView设置其宽、高为wrap_content时，ViewGroup会将其设置为AT_MOST；
    }//算出当子元素为wrapcontent时，父容器的大小

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("####################","isLAYOUT");
        Log.i("####################","isLAYOUT"+getChildCount());
        int childrenleft=0 ;
        int childrenright =0;
        int childrentop =0;
        int childrenbottom=0 ;
        childrenNum=getChildCount();
        for (int i=0;i<childrenNum;i++){
            childView = getChildAt(i);
            childrenWidth =childView.getMeasuredWidth();
            childrenHeight = childView.getMeasuredHeight();
            childrenParam =(MarginLayoutParams)childView.getLayoutParams();
            switch (i){
                case 0:
                    childrenleft = childrenParam.leftMargin;
                    childrentop = childrenParam.topMargin;
                    break;
                case 1:
                    childrenleft = getWidth()-childrenWidth-childrenParam.leftMargin;
                    childrentop = childrenParam.topMargin;
                    break;
                case 2:
                    childrenleft = childrenParam.leftMargin;
                    childrentop = getHeight()-childrenHeight- childrenParam.topMargin;
                    break;
                case 3:
                    childrenleft =getWidth()-childrenWidth-childrenParam.leftMargin;
                    childrentop = getHeight()-childrenHeight- childrenParam.topMargin;
                    break;

            }
            childrenright = childrenleft+childrenWidth;
            childrenbottom = childrentop+childrenHeight;

            Log.i("***********"+childView.getClass().getName(),"L"+childrenleft);
            Log.i("***********"+i,"T"+childrentop);
            Log.i("***********"+i,"R"+childrenright);
            Log.i("***********"+i,"B"+childrenbottom);
            Log.i("*getWidth"+this.getWidth(),"childrenWidth"+childrenWidth);
          childView.layout(childrenleft,childrentop,childrenright,childrenbottom);//相对于父容器左上角的距离
        }

    }//给子元素进行定位
}
