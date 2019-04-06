package com.shereen.sketchpad.view.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    private Point mPoint;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

        // initialize a triangle
//        mTriangle = new Triangle();

        mPoint = new Point();
    }

    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
//        float ratio = (float) width / height;

        final float ratio = (float) width / height;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f; //3
        final float far = 100.0f; //7


        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
    }

    private float[] rotationMatrix = new float[16];

    @Override
    public void onDrawFrame(GL10 gl) {


        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);


        float eyeX=0.0f;
        float eyeY=0.0f;
        float eyeZ=0.0f; //-3

        float centerX=0.0f;
        float centerY=0.0f;
        float centerZ=-5.0f; //0f

        float upX=0.0f;
        float upY=1.0f;
        float upZ=0.0f;

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        //****** Conintuous rotation

//        float[] scratch = new float[16];
//
//        //  Create a rotation transformation for the triangle
//        long time = SystemClock.uptimeMillis() % 4000L;
//        float angle = 0.090f * ((int) time);
//        Matrix.setRotateM(rotationMatrix, 0, angle, 0, 0, -1.0f);
//
//        // Combine the rotation matrix with the projection and camera view
//        // Note that the vPMatrix factor *must be first* in order
//        // for the matrix multiplication product to be correct.
//        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0);

        //******* Touch rotation

//        float[] scratch = new float[16];
//
//        // Create a rotation for the triangle
//        // long time = SystemClock.uptimeMillis() % 4000L;
//        // float angle = 0.090f * ((int) time);
//        Matrix.setRotateM(rotationMatrix, 0, mAngle, 0, 0, -1.0f);
//
//        // Combine the rotation matrix with the projection and camera view
//        // Note that the vPMatrix factor *must be first* in order
//        // for the matrix multiplication product to be correct.
//        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0);


//        mTriangle.draw(scratch);
        mPoint.draw();
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public volatile float mAngle;

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }
}
