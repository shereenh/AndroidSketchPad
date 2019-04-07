package com.shereen.sketchpad.view.helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.shereen.sketchpad.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

/**
 * Created by shereen on 4/6/19
 */

public class Utilities {

    public static String generateRandomName(){
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        return + n + ".jpg";
    }


}
