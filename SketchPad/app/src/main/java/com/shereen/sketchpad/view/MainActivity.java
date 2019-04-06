package com.shereen.sketchpad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shereen.sketchpad.Constants;
import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.dialog.Dialogs;
import com.shereen.sketchpad.viewmodel.MainViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements DrawFragment.OnFragmentInteractionListener {

    MainViewModel mainViewModel;
    DrawFragment drawFragment;
    Bitmap currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        init();
        listeners();
    }

    private void init(){
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        drawFragment = new DrawFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, drawFragment).commit();
    }


    private void saveImage(Bitmap image) {
        currentBitmap = image;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }else{
            Dialogs.showSketchNameDialog(this, currentBitmap);
        }

    }

    private void listeners(){

    }

    @Override
    public void onSaveBitmap(Bitmap bitmap) {
        saveImage(bitmap);
    }

    @Override
    public void getGalleryBackground() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveImage(currentBitmap);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.permission_denied)
                            , Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
