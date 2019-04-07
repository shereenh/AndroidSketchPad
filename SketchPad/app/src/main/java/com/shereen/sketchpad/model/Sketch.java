package com.shereen.sketchpad.model;

import android.graphics.Bitmap;

/**
 * Created by shereen on 4/6/19
 */

public class Sketch {

    Bitmap bitmap;
    String type;

    public Sketch(Bitmap bitmap, String type) {
        this.bitmap = bitmap;
        this.type = type;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
