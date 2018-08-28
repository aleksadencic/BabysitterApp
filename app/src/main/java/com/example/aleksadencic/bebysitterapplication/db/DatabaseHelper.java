package com.example.aleksadencic.bebysitterapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "post";
    private static final String COL1 = "name";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT," + COL1 + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        String sql = "DROP IF TABLE EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addUser(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Cursor getFields(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(sql, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT id FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(sql, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET " + COL1 + " = '" + newName + "' WHERE id " + " = " + id  + " AND " + COL1 + " = '" + oldName + "'";
        db.execSQL(sql);
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id " + " = " + id + " AND " + COL1 + " = '" + name + "'";
        db.execSQL(sql);
    }

}
