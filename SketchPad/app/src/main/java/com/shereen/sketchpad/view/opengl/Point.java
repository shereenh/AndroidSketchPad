package com.shereen.sketchpad.view.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Point {

    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    float[] vertices = {
            0.0f,0.0f,0.0f
    };
    FloatBuffer vertexBuf;
    private final int mProgram;


    public Point(){
        vertexBuf = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexBuf.put(vertices).position(0);

        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }

    final String vertexShaderCode =
            "uniform mat4 u_MVPMatrix;      \n"
                    + "attribute vec4 a_Position;     \n"
                    + "void main()                    \n"
                    + "{                              \n"
                    + "   gl_Position = uMVPMatrix   \n"
                    + "               * vPosition;   \n"
                    + "   gl_PointSize = 10.0;       \n"
                    + "}                              \n";

    final String fragmentShaderCode =
            "precision mediump float;       \n"
                    + "void main()                    \n"
                    + "{                              \n"
                    + "   gl_FragColor = vec4(1.0,    \n"
                    + "   1.0, 1.0, 1.0);             \n"
                    + "}                              \n";

    private int positionHandle;
    private int colorHandle;

    // Use to access and set the view transformation
    private int vPMatrixHandle;


    public void draw(){
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
//        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
//                GLES20.GL_FLOAT, false,
//                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        // Pass the projection and view transformation to the shader
//        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 1);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
