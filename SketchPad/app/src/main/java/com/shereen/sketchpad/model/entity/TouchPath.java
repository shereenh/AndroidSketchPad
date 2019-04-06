package com.shereen.sketchpad.model.entity;

import android.graphics.Path;

/**
 * Created by shereen on 4/4/19
 */

public class TouchPath {

    public int color;
    public boolean emboss;
    public boolean blur;
    public int strokeWidth;
    public Path path;

    public TouchPath(){

    }

    public TouchPath(int color, boolean emboss, boolean blur, int strokeWidth, Path path) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}
