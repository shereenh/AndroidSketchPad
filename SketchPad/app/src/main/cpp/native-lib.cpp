//
// Created by Shereen Hameed on 4/7/19.
//

#include <jni.h>
#include <string>

#include <math.h>
#include <android/bitmap.h>


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_shereen_sketchpad_view_MainActivity_anotherFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_shereen_sketchpad_view_DrawFragment_drawOnBitmap1(JNIEnv *env, jobject instance) {

    return env->NewStringUTF("Sketch:");
}extern "C"
JNIEXPORT void JNICALL
Java_com_shereen_sketchpad_view_DrawFragment_drawOnBitmap(JNIEnv *env, jobject instance,
                                                          jobject image, jlong elapsedTime) {

    AndroidBitmapInfo dstInfo;
    void* dstPixels;
//    AndroidBitmap_getInfo(env, image, &dstInfo);
//    AndroidBitmap_lockPixels(env, image, &dstPixels);



    // Unlock the dst's pixels
//    AndroidBitmap_unlockPixels(env, image);


}