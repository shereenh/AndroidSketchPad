package com.shereen.sketchpad.model.room;

import android.content.Context;

import com.shereen.sketchpad.Constants;
import com.shereen.sketchpad.model.entity.TouchPath;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


/**
 * Created by shereen on 4/5/19
 */
//@Database(entities = {TouchPath.class}, version = 1, exportSchema = false)
//@TypeConverters({TypeConverter.class})
public abstract class SketchDatabase  {

//    private static SketchDatabase INSTANCE = null;
//
//    public static SketchDatabase getInstance(Context context){
//        if (INSTANCE == null) {
//            synchronized (SketchDatabase.class){
//                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                        SketchDatabase.class,
//                        Constants.DATABASE_NAME)
//                        .build();
//            }
//        }
//        return INSTANCE;
//    }
//
//    public abstract TouchDao touchDao();
}
