package com.shereen.sketchpad.model;

import android.app.Application;

import com.shereen.sketchpad.model.entity.TouchPath;
import com.shereen.sketchpad.model.room.SketchDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;

import androidx.lifecycle.LiveData;

/**
 * Created by shereen on 4/5/19
 */

public class DataRepository {

    private ExecutorService executor;
    private static DataRepository INSTANCE = null;
//    private final TouchDao touchDao;

//    LiveData<List<TouchPath>> currentDrawing;

    private DataRepository(Application application, ExecutorService executor){
        this.executor = executor;
//        SketchDatabase database = SketchDatabase.getInstance(application);
//        touchDao = database.touchDao();
//        currentDrawing = touchDao.getTouchPaths();
    }

    public static DataRepository getInstance(Application application, ExecutorService executor){
        if(INSTANCE == null){
            synchronized (DataRepository.class){
                INSTANCE = new DataRepository(application, executor);
            }
        }
        return INSTANCE;
    }

    public void insertNewPath(final TouchPath path){
//        executor.execute(()->touchDao.insertPath(path));
    }

//    public LiveData<List<TouchPath>> getCurrentDrawing(){
//
//        return currentDrawing;
//    }

}
