package com.example.aleksadencic.bebysitterapplication.activities.notes;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.db.DatabaseHelper;

import java.util.ArrayList;

public class AllNotesAcitivity extends AppCompatActivity {

    private static final String TAG = "ListViewNotes";
    DatabaseHelper databaseHelper;
    ListView listViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes_acitivity);
        listViewNotes = (ListView) findViewById(R.id.listViewNotes);
        databaseHelper = new DatabaseHelper(AllNotesAcitivity.this);
        populateFields();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }

    private void populateFields() {
        Cursor data = databaseHelper.getFields();
        ArrayList<String> notes = new ArrayList<>();
        while(data.moveToNext()){
            notes.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listViewNotes.setAdapter(adapter);

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Cursor data = databaseHelper.getItemID(name);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Intent editScreenIntent = new Intent(AllNotesAcitivity.this, EditNoteActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    public void toastMessage(String message){
        Toast.makeText(AllNotesAcitivity.this, message, Toast.LENGTH_SHORT).show();
    }

}
