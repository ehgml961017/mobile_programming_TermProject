package com.example.termproject;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by 박도희 on 2016-11-29.
 */
public class FragmentHome extends Fragment {

    ArrayList<Timer> timerList = new ArrayList<Timer>(); //타이머들을 보관.
    View v;

    String[] categoryList = {"MOVING", "STUDYING", "RESTING", "EATING"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v=inflater.inflate(R.layout.fragment_home, null);

        Spinner categorySpinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, categoryList);
        categorySpinner.setAdapter(spinnerAdapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
