package com.shereen.sketchpad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shereen.sketchpad.Constants;
import com.shereen.sketchpad.R;
import com.shereen.sketchpad.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    DrawFragment drawFragment;
    OpenGLFragment openGLFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        init();
        listeners();
    }

    private void init(){
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        drawFragment = new DrawFragment();
        openGLFragment = new OpenGLFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, openGLFragment).commit();
    }

    private void listeners(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.draw: Log.d(Constants.LOGGER, "draw");
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.frameLayout, openGLFragment).commit();
                            break;

                            case R.id.saved: Log.d(Constants.LOGGER, "canvas");
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.frameLayout, drawFragment).commit();
                            break;

//                            case R.id.settings: Log.d(Constants.LOGGER, "sett"); break;

                        }
                        return true;
                    }
                });
    }
}
