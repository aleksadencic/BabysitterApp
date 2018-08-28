package com.example.aleksadencic.bebysitterapplication.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.aleksadencic.bebysitterapplication.R;

import java.util.Calendar;

public class NewPostActivity extends AppCompatActivity {

    Button btnDatePicker;
    Calendar calendar;
    DatePickerDialog dpd;
    EditText txtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

//        txtDate = (EditText) findViewById(R.id.txtDate);

        makeDatePicker();

    }

    private void makeDatePicker() {
        txtDate = (EditText) findViewById(R.id.txtDate);
        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(NewPostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        String year = mYear + "";
                        mMonth++;
                        String month = mMonth + "";
                        String day = mDay + "";
                        if(mMonth < 10)  month = "0" + mMonth;
                        if(mDay < 10) day = "0" + mDay;
                        txtDate.setText(year + "-" + month + "-" + day);
                    }
                }, year, month, day);
                dpd.show();
            }
        });
    }

}
