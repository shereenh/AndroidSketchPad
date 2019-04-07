package com.shereen.sketchpad.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.shereen.sketchpad.model.Sketch;
import com.shereen.sketchpad.view.helper.Constants;
import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.helper.Dialogs;
import com.shereen.sketchpad.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    DrawFragment drawFragment;
    MenuFragment menuFragment;
    Sketch currentBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        init();
        observers();

    }

    private void init() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        drawFragment = new DrawFragment();
        menuFragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.canvasFrame, drawFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.menuFrame, menuFragment).commit();
    }

    private void observers() {
        mainViewModel.getLiveBitmap()
                .observe(this, bitmap -> {
                    currentBitmap = bitmap;
                    if (bitmap.getType().equals(Constants.SAVE)) {
                        saveImage();
                    } else {
                        shareImage();
                    }
                });
    }

    private void saveImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            Dialogs.showSketchNameDialog(this, currentBitmap.getBitmap());
        }
    }

    private void shareImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            //code to share
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length <= 0
                && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.permission_denied), Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (currentBitmap.getType().equals(Constants.SAVE)) {
                    saveImage();
                } else {
                    shareImage();
                }
            }
            break;
            case Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                shareImage();
                break;
        }
    }
}
