package com.shereen.sketchpad.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.shereen.sketchpad.Constants;
import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.draw.SketchView;
import com.shereen.sketchpad.view.draw.TouchPath;
import com.shereen.sketchpad.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawFragment extends Fragment implements SketchView.FragmentCallback {

    MainViewModel mViewModel;

    private SketchView sketchView;
    View rootView;
    private Spinner spinner1, spinner2;

    public DrawFragment() {
        // Required empty public constructor
    }

//    SketchView.FragmentCallback mCallback = new SketchView.FragmentCallback() {
//        @Override
//        public void onNewTouchPath(TouchPath path) {
//            Log.d(Constants.LOGGER, "Got new path");
//        }
//    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_draw, container, false);
        init();

        addListenerOnSpinnerItemSelection();

        return rootView;
    }

    private void init(){
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        sketchView = (SketchView) rootView.findViewById(R.id.sketchView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        sketchView.setCallback(this);
        sketchView.init(displayMetrics);
        sketchView.normal();
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(getActivity(), item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Selected",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


        spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
        spinner2.setAdapter(new SpinnerAdapter(getActivity()));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(getActivity(), item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Selected",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onNewTouchPath(TouchPath path) {
        Log.d(Constants.LOGGER, "Got new path 123");
    }
}
