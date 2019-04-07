package com.shereen.sketchpad.viewmodel;

import android.app.Application;
import android.app.SharedElementCallback;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.shereen.sketchpad.model.DataRepository;
import com.shereen.sketchpad.model.Sketch;
import com.shereen.sketchpad.view.draw.SketchView;
import com.shereen.sketchpad.view.helper.Constants;
import com.shereen.sketchpad.view.helper.Utilities;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by shereen on 4/4/19
 */

public class MainViewModel extends AndroidViewModel {

    private SketchView sketchView;
    private MutableLiveData<Sketch> liveBitmap;
//    DataRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        liveBitmap = new MutableLiveData<>();
//        mRepository = DataRepository.getInstance(application, Executors.newSingleThreadExecutor());

    }

    public void setSketchView(SketchView view, WindowManager windowManager){
        sketchView = view;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        sketchView.init(displayMetrics);
        sketchView.normal();
    }

    public SketchView getSketchView(){
        return sketchView;
    }

    public void clearSketchView(){
        sketchView.clear();
    }

    public void undoSketchView(){
        sketchView.undo();
    }

    public void saveBitmap(){
        liveBitmap.setValue(new Sketch(sketchView.getmBitmap(), Constants.SAVE));
    }

    public void shareBitmap(){
        liveBitmap.setValue(new Sketch(sketchView.getmBitmap(), Constants.SHARE));
    }

    public LiveData<Sketch> getLiveBitmap(){
        return liveBitmap;
    }

    public Bitmap getBitmap(){
        return sketchView.getmBitmap();
    }

    public void changeBackground(int color){
        Log.d(Constants.LOGGER, "changing background");
        sketchView.changeBackground(color);
    }

    public void changePencilColor(int color){
        sketchView.changeColor(color);
    }

}
