package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements EditFragment.OnCityEditedListener {

    private ArrayList<City> cityListData;
    private ArrayAdapter<City> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityListData = new ArrayList<>();
        cityListData.add(new City("Edmonton", "AB"));
        cityListData.add(new City("Vancouver", "BC"));
        cityListData.add(new City("Montreal", "QC"));
        cityListData.add(new City("Toronto", "ON"));

        ListView cityList = findViewById(R.id.city_list);
        cityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                cityListData);

        cityList.setAdapter(cityAdapter);

        //briefly hold click to enable editing
        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            City city = cityListData.get(position);

            EditFragment fragment =
                    EditFragment.newInstance(city, position);
            fragment.show(getSupportFragmentManager(), "EDIT_CITY");

            return true;
        });
    }

    @Override
    public void onCityEdited(int position, City city) {
        cityListData.set(position, city);
        cityAdapter.notifyDataSetChanged();
    }
}
