package com.shereen.sketchpad.model;

import android.app.Application;

import com.shereen.sketchpad.view.draw.TouchPath;

import java.util.concurrent.ExecutorService;

/**
 * Created by shereen on 4/5/19
 */

public class DataRepository {

    private ExecutorService executor;
    private static DataRepository INSTANCE = null;


    private DataRepository(Application application, ExecutorService executor){
        this.executor = executor;

    }

    public static DataRepository getInstance(Application application, ExecutorService executor){
        if(INSTANCE == null){
            synchronized (DataRepository.class){
                INSTANCE = new DataRepository(application, executor);
            }
        }
        return INSTANCE;
    }

}
