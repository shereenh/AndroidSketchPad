package com.shereen.sketchpad.view;


import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.opengl.MyGLSurfaceView;
import com.shereen.sketchpad.view.opengl.Triangle;
import com.shereen.sketchpad.view.opengl.sample.MySurfaceView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenGLFragment extends Fragment {

    private GLSurfaceView mGLView;


    public OpenGLFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflater.inflate(R.layout.fragment_open_gl, container, false);
        mGLView = new MySurfaceView(getActivity());
        return mGLView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
