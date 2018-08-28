package com.example.aleksadencic.bebysitterapplication.activities.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.db.DatabaseHelper;

public class MainNotesActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button btnInsert, btnViewAll;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notes);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameField = (EditText) findViewById(R.id.txtNote);
                databaseHelper = new DatabaseHelper(MainNotesActivity.this);
                String name = nameField.getText().toString();
                if ( name.length() != 0 ) addUser(name);
                else toastMessage("You must enter note!");
                nameField.setText("");
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNotesActivity.this, AllNotesAcitivity.class);
                startActivity(intent);
            }
        });
    }

    public void addUser(String name){
        boolean insertUser = databaseHelper.addUser(name);
        if(insertUser)
            toastMessage("Data Successfully Inserted!");
        else
            toastMessage("Something went wrong!");
    }

    public void toastMessage(String message){
        Toast.makeText(MainNotesActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
