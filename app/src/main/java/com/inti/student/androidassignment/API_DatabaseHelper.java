package com.inti.student.androidassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class API_DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "api_List.db";
    public static final String TABLE_API = "apiList_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "WEBSITE_NAME";
    public static final String COL3 = "URL";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_API + "("
            + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL2 + " TEXT,"
            + COL3 + " TEXT);";



    public API_DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(API_DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_API);
        onCreate(db);
    }

    public boolean addData(String websiteName,String url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, websiteName);
        contentValues.put(COL3, url);


        long result = db.insert(TABLE_API, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            System.out.print("Saved");
            return true;
        }
    }

    public Cursor getAPI() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_API, null);

        return cursor;
    }

    public void deleteALL(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_API;

        db.execSQL(query);
    }

}