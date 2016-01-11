package com.xiaoliu.my2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/11.
 */
public class Card extends FrameLayout {
    private TextView numTv;
    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num<=0){
            numTv.setText("");
        }else {
            numTv.setText(num+"");
        }
    }

    public Card(Context context) {
        super(context);
        init();
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Card(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        numTv = new TextView(getContext());
        numTv.setTextSize(30);
        numTv.setBackgroundColor(0x33ffffff);
        numTv.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(-1,-1);
        lp.setMargins(10, 10, 0, 0);
        addView(numTv, lp);
    }

    public boolean equals(Card o) {
        return getNum()==o.getNum();
    }
}
