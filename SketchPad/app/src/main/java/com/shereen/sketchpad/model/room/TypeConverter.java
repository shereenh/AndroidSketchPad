package com.shereen.sketchpad.model.room;

import android.graphics.Path;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by shereen on 4/5/19
 */

public class TypeConverter {

    static Gson gson = new Gson();

    @androidx.room.TypeConverter
    public static Path fromString(String value){

        Type pathType = new TypeToken<Path>(){}.getType();
        return gson.fromJson(value, pathType);
    }

    @androidx.room.TypeConverter
    public static String fromPath(Path path){
        String json = gson.toJson(path);
        return json;
    }
}
