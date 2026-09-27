package com.example.musem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

     static final String DATABASE_NAME = "itinerary_db";
     static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "itinerary";
    public static final String COLUMN_ID = "id";
    public    static final String COLUMN_NAME = "name";
    public   static final String COLUMN_LOCATION = "location";
    public   static final String COLUMN_TIME = "time";
    public    static final String COLUMN_DATE = "date";
    public  static final String COLUMN_IMAGE = "image";  // New column to store image resource ID

    // SQL query to create the table with the new "image" column
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_LOCATION + " TEXT, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_IMAGE + " INTEGER" +  // Add image as an INTEGER type column
            ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);  // Create the table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert data into the table, including the image resource ID
    public long addItinerary(String name, String location, String time, String date, int imageResourceId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_IMAGE, imageResourceId);  // Store image resource ID as an integer

        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor getAllItineraries() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public int deleteItinerary(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
