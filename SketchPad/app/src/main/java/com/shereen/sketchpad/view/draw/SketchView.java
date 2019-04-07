package com.shereen.sketchpad.view.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.helper.Constants;
import com.shereen.sketchpad.view.helper.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shereen on 4/4/19
 */

public class SketchView extends View {

    private float mX, mY;
    private Path mPath;
    private TouchPath fp;
    private Paint mPaint;
    private ArrayList<TouchPath> paths = new ArrayList<>();
    private int currentColor;
    private int backgroundColor = Constants.DEFAULT_BG_COLOR;
    private int strokeWidth;
    private boolean emboss;
    private boolean blur;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    private boolean isBackgroundColor = true;

    int height;
    int width;

    public boolean isBackgroundColor(){
        return isBackgroundColor;
    }

    public void setIfBackgroundColor(boolean value){
        isBackgroundColor = value;
    }
    
    public SketchView(Context context) {
        this(context, null);
    }

    public SketchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
//        mPaint.setColor(Constants.COLOR_RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);

        mEmboss = new EmbossMaskFilter(new float[] {1, 1, 1}, 0.4f, 6, 3.5f);
        mBlur = new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL);

    }


    public void drawPrevious(List<TouchPath> oldPaths){
        paths.clear();
        paths.addAll(oldPaths);
        invalidate();
    }

    public void init(DisplayMetrics metrics) {
        height = metrics.heightPixels;
        width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        currentColor = Constants.COLOR_RED;
        strokeWidth = Constants.BRUSH_SIZE;
    }

    public Bitmap getmBitmap(){
        return mBitmap;
    }

    public void normal() {
        emboss = false;
        blur = false;
    }

    public void changeColor(int color){
        currentColor = color;
    }

    public void changeBackground(int color){
        backgroundColor = color;
        invalidate();
    }

    public void emboss() {
        emboss = true;
        blur = false;
    }

    public void blur() {
        emboss = false;
        blur = true;
    }

    public void clear() {
//        backgroundColor = Constants.COLOR_WHITE;
        paths.clear();
        normal();
        invalidate();
    }

    public void undo(){
        if(!paths.isEmpty()){
            paths.remove(paths.size()-1);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Log.d(Constants.LOGGER, "onDraw");
        canvas.save();
        if(isBackgroundColor){
            mCanvas.drawColor(backgroundColor);
        }

        for (TouchPath fp : paths) {
            mPaint.setColor(fp.color);
            mPaint.setStrokeWidth(fp.strokeWidth);
            mPaint.setMaskFilter(null);

            if (fp.emboss)
                mPaint.setMaskFilter(mEmboss);
            else if (fp.blur)
                mPaint.setMaskFilter(mBlur);

            mCanvas.drawPath(fp.path, mPaint);

        }

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y) {
//        Log.d(Constants.LOGGER, "touchStart");

        mPath = new Path();
        fp = new TouchPath(currentColor, emboss, blur, strokeWidth, mPath);
        paths.add(fp);

        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= Constants.TOUCH_TOLERANCE || dy >= Constants.TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                invalidate();
                break;
        }

        return true;
    }
}
