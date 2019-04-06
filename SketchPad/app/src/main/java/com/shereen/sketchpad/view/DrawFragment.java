package com.shereen.sketchpad.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import com.shereen.sketchpad.Constants;
import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.adapters.ColorAdapter;
import com.shereen.sketchpad.view.draw.SketchView;
import com.shereen.sketchpad.model.entity.TouchPath;
import com.shereen.sketchpad.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawFragment extends Fragment implements SketchView.FragmentCallback {

    MainViewModel mViewModel;

    private SketchView sketchView;
    View rootView;
    private Spinner pencilSpinner, backgroundSpinner;
    private ImageButton eraseButton, undoButton, saveButton, galleryButton;

    private OnFragmentInteractionListener mListener;


    public DrawFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_draw, container, false);
        init();
        listeners();

        return rootView;
    }

    private void init(){
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        eraseButton = rootView.findViewById(R.id.eraseButton);
        undoButton = rootView.findViewById(R.id.undoButton);
        saveButton = rootView.findViewById(R.id.saveButton);

        backgroundSpinner = rootView.findViewById(R.id.backgroundSpinner);
        backgroundSpinner.setAdapter(new ColorAdapter(getActivity(), false));
        backgroundSpinner.setSelection(7);

        pencilSpinner = rootView.findViewById(R.id.pencilSpinner);
        pencilSpinner.setAdapter(new ColorAdapter(getActivity(), true));

        sketchView = rootView.findViewById(R.id.sketchView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        sketchView.setCallback(this);
        sketchView.init(displayMetrics);
        sketchView.normal();
    }


    public void listeners() {

        eraseButton.setOnClickListener(v -> sketchView.clear());

        undoButton.setOnClickListener(v -> sketchView.undo());

        saveButton.setOnClickListener(v -> {
            if(mListener != null){
                mListener.onSaveBitmap(sketchView.getmBitmap());
            }
        });

        galleryButton.setOnClickListener(v -> {
            if(mListener != null) mListener.getGalleryBackground();
        });

        backgroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Integer item = (Integer) adapterView.getItemAtPosition(position);
                if (item != null) sketchView.changeBackground(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        pencilSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Integer item = (Integer) adapterView.getItemAtPosition(position);
                if (item != null) sketchView.changeColor(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    @Override
    public void onNewTouchPath(TouchPath path) {
        Log.d(Constants.LOGGER, "Got new path 123");
//        mViewModel.addStroke(path);
    }

    //    SketchView.FragmentCallback mCallback = new SketchView.FragmentCallback() {
//        @Override
//        public void onNewTouchPath(TouchPath path) {
//            Log.d(Constants.LOGGER, "Got new path");
//        }
//    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onSaveBitmap(Bitmap bitmap);
        void getGalleryBackground();
    }
}
