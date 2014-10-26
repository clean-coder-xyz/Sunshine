package com.cleancoder.sunshine.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cleancoder.sunshine.app.data.WeatherContract.*;

/**
 * Created by Leonid Semyonov (clean-coder-xyz) on 25.10.2014.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE_WEATHER =
                "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +
                        WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        WeatherEntry.COLUMN_LOCATION_ID + " INTEGER NOT NULL, " +
                        WeatherEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                        WeatherEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                        WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL, " +
                        WeatherEntry.COLUMN_MIN_TEMPERATURE + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_MAX_TEMPERATURE + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_HUMIDITY + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_PRESSURE + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_DEGREES + " REAL NOT NULL, " +

                        " FOREIGN KEY (" + WeatherEntry.COLUMN_LOCATION_ID + ") REFERENCES " +
                        LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "), " +

                        " UNIQUE (" + WeatherEntry.COLUMN_DATE + ", " +
                        WeatherEntry.COLUMN_LOCATION_ID + ") ON CONFLICT REPLACE" +
                ");";

        final String SQL_CREATE_TABLE_LOCATION =
                "CREATE TABLE " + LocationEntry.TABLE_NAME + " (" +
                        LocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        LocationEntry.COLUMN_LOCATION_SETTING + " TEXT UNIQUE NOT NULL, " +
                        LocationEntry.COLUMN_LOCATION_NAME + " TEXT NOT NULL, " +
                        LocationEntry.COLUMN_LATITUDE + " REAL NOT NULL, " +
                        LocationEntry.COLUMN_LONGITUDE + " REAL NOT NULL, " +
                        "UNIQUE (" + LocationEntry.COLUMN_LOCATION_SETTING + ") ON CONFLICT IGNORE" +
                ");";

        db.execSQL(SQL_CREATE_TABLE_LOCATION);
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME);
        onCreate(db);
    }

}
