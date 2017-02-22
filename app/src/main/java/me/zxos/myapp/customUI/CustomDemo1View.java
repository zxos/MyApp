package me.zxos.myapp.customUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zxos on 17/2/19.
 */

public class CustomDemo1View extends View {


    private Paint mPaint;
    private Path  mPath;
    private Rect  mRect;
    private String text = "测试";


    public CustomDemo1View(Context context) {
        super(context, null);

        init();
    }

    public CustomDemo1View(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        init();
    }

    public CustomDemo1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init(){

        if (mPaint == null){

            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


            mRect  = new Rect();
            mPaint.setTextSize(30);
            mPaint.getTextBounds(text,0,text.length(),mRect);
        }
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);   //看着父控件宽 或 设置的实际值
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);

        int width;
        int height;

        if (widthmode == MeasureSpec.EXACTLY){  //设置实际值时
            width = widthSize;
        }else{
            width = mRect.width()*2;
        }

        if (heightmode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            height = mRect.height()*2;
        }

        setMeasuredDimension(width, height);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawText(text,getWidth()/2-mRect.width()/2, getHeight()/2-mRect.height()/2, mPaint);
    }


}
