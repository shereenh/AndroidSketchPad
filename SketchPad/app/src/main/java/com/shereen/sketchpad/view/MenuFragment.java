package com.shereen.sketchpad.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.shereen.sketchpad.R;
import com.shereen.sketchpad.view.adapters.ColorAdapter;
import com.shereen.sketchpad.view.helper.Constants;
import com.shereen.sketchpad.view.helper.Utilities;
import com.shereen.sketchpad.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    MainViewModel mViewModel;

    private Spinner pencilSpinner, backgroundSpinner;
    private ImageButton eraseButton, undoButton, saveButton, shareButton;

    private View rootView;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        init();
        listeners();
        return rootView;
    }

    private void init(){
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        eraseButton = rootView.findViewById(R.id.eraseButton);
        undoButton = rootView.findViewById(R.id.undoButton);
        saveButton = rootView.findViewById(R.id.saveButton);
        shareButton = rootView.findViewById(R.id.shareButton);

        backgroundSpinner = rootView.findViewById(R.id.backgroundSpinner);
        backgroundSpinner.setAdapter(new ColorAdapter(getActivity(), false));
        backgroundSpinner.setSelection(7);

        pencilSpinner = rootView.findViewById(R.id.pencilSpinner);
        pencilSpinner.setAdapter(new ColorAdapter(getActivity(), true));
    }

    public void listeners() {

        eraseButton.setOnClickListener(v -> mViewModel.clearSketchView());

        undoButton.setOnClickListener(v -> mViewModel.undoSketchView());

        saveButton.setOnClickListener(v -> mViewModel.saveBitmap());

        shareButton.setOnClickListener(v -> mViewModel.shareBitmap());

        backgroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Integer item = (Integer) adapterView.getItemAtPosition(position);
                if (item != null) mViewModel.changeBackground(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        pencilSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Integer item = (Integer) adapterView.getItemAtPosition(position);
                if (item != null) mViewModel.changePencilColor(item);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

//    private void selectFromGallery(){
//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        getActivity().startActivityForResult(photoPickerIntent, Constants.MY_PERMISSIONS_REQUEST_GALLERY_ACCESS);
//        Log.d(Constants.LOGGER, "starting gallery");
//    }

}
