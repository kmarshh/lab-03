package com.example.listycity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class EditFragment extends DialogFragment {

    private static final String ARG_CITY = "city";
    private static final String ARG_POSITION = "position";

    private City city;
    private int position;

    public interface OnCityEditedListener {
        void onCityEdited(int position, City city);
    }

    public static EditFragment newInstance(City city, int position) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public EditFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable(ARG_CITY);
            position = getArguments().getInt(ARG_POSITION);
        }

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.edit_city, null);

        EditText nameInput = view.findViewById(R.id.edit_city_name);
        EditText provinceInput = view.findViewById(R.id.edit_city_province);

        nameInput.setText(city.getName());
        provinceInput.setText(city.getProvince());

        return new AlertDialog.Builder(requireContext())
                .setTitle("Edit City")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {
                    city.setName(nameInput.getText().toString());
                    city.setProvince(provinceInput.getText().toString());

                    OnCityEditedListener listener =
                            (OnCityEditedListener) getActivity();
                    listener.onCityEdited(position, city);
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}
