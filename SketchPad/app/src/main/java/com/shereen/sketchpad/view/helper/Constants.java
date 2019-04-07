package com.shereen.sketchpad.view.helper;

import android.graphics.Color;

import com.shereen.sketchpad.R;

/**
 * Created by shereen on 4/4/19
 */

public class Constants {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 9078;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 9077;

    public static final String LOGGER = "SKETCH-PAD";

    // sketching constants
    public static int BRUSH_SIZE = 20;
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    public static final float TOUCH_TOLERANCE = 4;

    public static final int COLOR_RED = R.color.red;
    public static final int COLOR_ORANGE = R.color.orange;
    public static final int COLOR_YELLOW = R.color.yellow;
    public static final int COLOR_GREEN = R.color.green;
    public static final int COLOR_BLUE = R.color.blue;
    public static final int COLOR_PURPLE = R.color.purple;
    public static final int COLOR_BLACK = R.color.black;
    public static final int COLOR_WHITE = R.color.white;

    // room constants
    public static final String DATABASE_NAME = "sketchpad.db";
    public static final String TOUCH_PATH_TABLE = "touch_path";

    //share
    public static final String SHARE = "share";
    public static final String SAVE = "save";
}
