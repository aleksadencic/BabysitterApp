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

public class EditNoteActivity extends AppCompatActivity {

    private static final String TAG = "EditNoteActivity";

    private Button btnSave, btnDelete;
    private EditText editable_item;
    DatabaseHelper databaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.txtNote);
        databaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");

        editable_item.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    databaseHelper.updateName(item,selectedID,selectedName);
                    toastMessage("Note is saved");
                    finish();
                }else{
                    toastMessage("You must enter a note");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("Note removed from the database");
                finish();
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
