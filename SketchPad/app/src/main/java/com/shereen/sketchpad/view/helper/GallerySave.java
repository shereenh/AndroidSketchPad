package com.shereen.sketchpad.view.helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.shereen.sketchpad.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by shereen on 4/6/19
 */

public class GallerySave {

    public static void storeFile(Context context, File storageDir, String sketchName, Bitmap bitmap){
        File imageFile = new File(storageDir, sketchName + ".jpg");
        String savedImagePath = imageFile.getAbsolutePath();
        try {
            OutputStream fOut = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        galleryAddPic(context, savedImagePath);
    }

    public static void getSketchName(Context context, String sketchName, Bitmap bitmap){
        Log.d(Constants.LOGGER, "getSketchName: "+ sketchName);
        if(sketchName.length() == 0){
            sketchName = Utilities.generateRandomName();
        }

        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/Sketch");

        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            // Add the image to the system gallery
            storeFile(context, storageDir, sketchName, bitmap);

            Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.save_success)
                    , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.save_fail)
                    , Toast.LENGTH_LONG).show();
        }

    }

    private static void galleryAddPic(Context context, String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }
}
