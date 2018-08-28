package com.example.aleksadencic.bebysitterapplication.activities.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.domain.City;
import com.example.aleksadencic.bebysitterapplication.domain.Settlement;
import com.example.aleksadencic.bebysitterapplication.domain.User;
import com.example.aleksadencic.bebysitterapplication.logic.Controller;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    Spinner spinnerCity, spinnerSettlement;
    Button btnDatePicker, btnSearch;
    Calendar calendar;
    DatePickerDialog dpd;
    EditText date;
    View view;
    List<City> cities;
    List<Settlement> settlements;
    User user;
    TextView tvCaption;

    public SearchFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        user = (User) getArguments().getParcelable("user");
        date = (EditText) view.findViewById(R.id.txtDate);
        tvCaption = (TextView) view.findViewById(R.id.tvCaption);

        makeSpinnerCity();
        makeSpinnerSettlement();
        makeDatePicker();

        btnSearch = (Button) view.findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Searching...", Toast.LENGTH_SHORT).show();
                String settlement = spinnerSettlement.getSelectedItem().toString();

                String dateString = (new Date()).toString();
                if(date.getText().toString().trim().equalsIgnoreCase("")){
                    dateString = date.getText().toString();
                }

                Bundle bundle = new Bundle();
                bundle.putParcelable("user", user);
                bundle.putString("settlement", settlement);
                bundle.putString("date", dateString);


                // Create new fragment and transaction
                HomeFragment homeFragment = new HomeFragment(true);
                homeFragment.setArguments(bundle);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed

                transaction.replace(R.id.constraintLayout, homeFragment);
                //transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();

                //not best way -> the best way is to remove fragment from stack
                btnSearch.setVisibility(View.INVISIBLE);
                btnDatePicker.setVisibility(View.INVISIBLE);
                spinnerSettlement.setVisibility(View.INVISIBLE);
                spinnerCity.setVisibility(View.INVISIBLE);
                date.setVisibility(View.INVISIBLE);
                tvCaption.setVisibility(View.INVISIBLE);


            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String city = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), city, Toast.LENGTH_SHORT).show();
                makeSpinnerSettlement();
            }

            @Override
            public void onNothingSelected(AdapterView<?> position) {
            }
        });

        return view;
    }


    private void makeSpinnerCity() {
        spinnerCity = (Spinner) view.findViewById(R.id.spinnerCity);
        cities = Controller.getInstance().getAllCities();
        List<String> citiesNames = new ArrayList<>();
        for(City city: cities){
            citiesNames.add(city.getName());
        }
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, citiesNames);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);
        //spinnerCity.setOnItemSelectedListener(this);
    }

    private void makeSpinnerSettlement() {
        spinnerSettlement = (Spinner) view.findViewById(R.id.spinnerSettlement);
        spinnerCity = (Spinner) view.findViewById(R.id.spinnerCity);
        String selectedCityName = spinnerCity.getSelectedItem().toString();
        City selectedCity = new City();
        for(City city: cities){
            if(city.getName().equalsIgnoreCase(selectedCityName)){
                selectedCity = city;
                break;
            }
        }
        settlements = Controller.getInstance().getSettlements(selectedCity);
        List<String> settlementsNames = new ArrayList<>();
        for(Settlement settlement: settlements){
            settlementsNames.add(settlement.getName());
        }
        ArrayAdapter<String> adapterSettlement = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, settlementsNames);
        adapterSettlement.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSettlement.setAdapter(adapterSettlement);
        //spinnerSettlement.setOnItemSelectedListener(this);

    }

    private void makeDatePicker() {
        date = (EditText) view.findViewById(R.id.txtDate);
        btnDatePicker = (Button) view.findViewById(R.id.btnDatePicker);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        String year = mYear + "";
                        mMonth++;
                        String month = mMonth + "";
                        String day = mDay + "";
                        if(mMonth < 10)  month = "0" + mMonth;
                        if(mDay < 10) day = "0" + mDay;
                        date.setText(year + "-" + month + "-" + day);
                    }
                }, year, month, day);
                dpd.show();
            }
        });
    }

}
