package com.example.vita.drawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DrawViewNew mDrawView =null;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView =(TextView) findViewById(R.id.txtShow3);

        mDrawView = (DrawViewNew) findViewById(R.id.draw_view);
//        mDrawView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mTextView.setText("点击了自定义控件");
//                return true;
//            }
//
//        });
        mDrawView.setClickable(true);
        mDrawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText("点击了自定义控件");
            }
        });
    }
}
