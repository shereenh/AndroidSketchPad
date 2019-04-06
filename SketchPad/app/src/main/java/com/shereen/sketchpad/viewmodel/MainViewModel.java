package com.shereen.sketchpad.viewmodel;

import android.app.Application;

import com.shereen.sketchpad.model.DataRepository;
import com.shereen.sketchpad.model.entity.TouchPath;

import java.util.List;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by shereen on 4/4/19
 */

public class MainViewModel extends AndroidViewModel {

//    private TouchPath curTouchPath;

    DataRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = DataRepository.getInstance(application, Executors.newSingleThreadExecutor());

    }

//    public void setCurTouchPath(TouchPath curTouchPath) {
//        this.curTouchPath = curTouchPath;
//    }

//    public void addStroke(TouchPath tp){
//        mRepository.insertNewPath(tp);
//    }

//    public LiveData<List<TouchPath>> getDrawing(){
//        return mRepository.getCurrentDrawing();
//    }
}
