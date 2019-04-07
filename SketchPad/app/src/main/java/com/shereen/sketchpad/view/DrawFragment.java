package com.shereen.sketchpad.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.shereen.sketchpad.view.helper.Constants;
import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.adapters.ColorAdapter;
import com.shereen.sketchpad.view.draw.SketchView;
import com.shereen.sketchpad.view.draw.TouchPath;
import com.shereen.sketchpad.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawFragment extends Fragment {

    MainViewModel mViewModel;
    View rootView;


    public DrawFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_draw, container, false);
        init();

        return rootView;
    }

    private void init(){
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        SketchView sketchView = rootView.findViewById(R.id.sketchView);
        mViewModel.setSketchView(sketchView, getActivity().getWindowManager());
    }
}
