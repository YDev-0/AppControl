package com.control.appcontrol;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ConvertFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner currentDegreeSpinner;
    private Spinner selectDegreeSpinner;
    private EditText currentValue;
    private EditText desiredValue;

    private Button convertButton;

    public ConvertFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert, container, false);
        currentValue = view.findViewById(R.id.currentValue);
        desiredValue = view.findViewById(R.id.desiredValue);
        convertButton = view.findViewById(R.id.convertButton);
        currentDegreeSpinner = view.findViewById(R.id.current_degree_spinner);
        selectDegreeSpinner = view.findViewById(R.id.select_degree_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currentDegreeSpinner.setAdapter(adapter);
        selectDegreeSpinner.setAdapter(adapter);
        currentDegreeSpinner.setOnItemSelectedListener(this);
        selectDegreeSpinner.setOnItemSelectedListener(this);

        currentValue.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = currentValue.getText().toString();

                if(text.length() > 0) {
                    try {
                        double value = Double.parseDouble(text);
                    } catch (Exception e) {
                        currentValue.setText(text.substring(0, text.length() - 1));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        convertButton.setOnClickListener(v -> {
            String currentDegree = currentDegreeSpinner.getSelectedItem().toString();
            String desiredDegree = selectDegreeSpinner.getSelectedItem().toString();
            convert(currentDegree, desiredDegree);
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String currentDegree = currentDegreeSpinner.getSelectedItem().toString();
        String desiredDegree = selectDegreeSpinner.getSelectedItem().toString();
        desiredValue.setText("0");
        convertButton.setEnabled(!currentDegree.equals(desiredDegree));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void convert(String currentDegree, String desiredDegree) {
        double value = Double.parseDouble(currentValue.getText().toString());

        switch(desiredDegree) {
            case "C°": {
                value = (currentDegree.equals("K°") ? (value - 273.15) : ((value - 32) * 5 / 9));
                break;
            }
            case "F°": {
                value = (currentDegree.equals("C°") ? ((value * 9 / 5) + 32) : (((value - 273.15) * 9 / 5) + 32));
                break;
            }
            case "K°": {
                value = (currentDegree.equals("C°") ? (value + 273.15) : (((value - 32) * 5 / 9) + 273.15));
                break;
            }
        }

        desiredValue.setText("" + value);
    }
}