package com.shereen.sketchpad.viewmodel;

import android.app.Application;

import com.shereen.sketchpad.view.draw.TouchPath;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Created by shereen on 4/4/19
 */

public class MainViewModel extends AndroidViewModel {

    private TouchPath curTouchPath;
    private List<TouchPath> curDrawing;

    public MainViewModel(@NonNull Application application) {
        super(application);

        curDrawing = new ArrayList<>();

    }

    public void setCurTouchPath(TouchPath curTouchPath) {
        this.curTouchPath = curTouchPath;
    }
}
