package com.shereen.sketchpad.view;

import java.util.Random;

/**
 * Created by shereen on 4/6/19
 */

public class Utilities {

    public static String generateRandomName(){

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);

        return + n + ".jpg";
    }
}
