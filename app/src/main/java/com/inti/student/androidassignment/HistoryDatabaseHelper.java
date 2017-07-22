package com.inti.student.androidassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HistoryDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "history_List.db";
    public static final String TABLE_HISTORY = "historyList_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "AUTHOR";
    public static final String COL3 = "URL_IMAGE";
    public static final String COL4 = "NEWS_TITLE";
    public static final String COL5 = "NEWS_DESCRIPTION";
    public static final String COL6 = "DATE_TIME";
    public static final String COL7 = "URL";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_HISTORY + "("
            + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL2 + " TEXT,"
            + COL3 + " TEXT,"
            + COL4 + " TEXT,"
            + COL5 + " TEXT,"
            + COL6 + " TEXT,"
            + COL7 + " TEXT);";



    public HistoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(HistoryDatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_HISTORY);
        onCreate(db);
    }

    public boolean addData(String author,String urlImage,String title, String description,String dateTime,String url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, author);
        contentValues.put(COL3, urlImage);
        contentValues.put(COL4, title);
        contentValues.put(COL5, description);
        contentValues.put(COL6, dateTime);
        contentValues.put(COL7, url);


        long result = db.insert(TABLE_HISTORY, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            System.out.print("Saved");
            return true;
        }
    }


    public void deleteALL(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_HISTORY;

        db.execSQL(query);
    }


    public Cursor getHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HISTORY, null);

        return cursor;
    }
}