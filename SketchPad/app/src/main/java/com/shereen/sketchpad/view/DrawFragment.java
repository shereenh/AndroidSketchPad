package com.shereen.sketchpad.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.draw.SketchView;
import com.shereen.sketchpad.viewmodel.MainViewModel;


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
