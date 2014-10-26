package com.cleancoder.sunshine.app.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.cleancoder.sunshine.app.data.WeatherContract.*;

/**
 * Created by Leonid Semyonov (clean-coder-xyz) on 25.10.2014.
 */
public class WeatherProvider extends ContentProvider {

    public static final int WEATHER = 100;
    public static final int WEATHER_WITH_LOCATION = 101;
    public static final int WEATHER_WITH_LOCATION_AND_DATE = 102;
    public static final int LOCATION = 300;
    public static final int LOCATION_ID = 301;


    private static final UriMatcher uriMatcher = buildUriMatcher();

    private WeatherDbHelper dbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = WeatherContract.CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, WeatherContract.PATH_WEATHER, WEATHER);
        uriMatcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
        uriMatcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/*", WEATHER_WITH_LOCATION_AND_DATE);
        uriMatcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);
        uriMatcher.addURI(authority, WeatherContract.PATH_LOCATION + "/#", LOCATION_ID);
        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        dbHelper = new WeatherDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor result;
        switch (uriMatcher.match(uri)) {
            case WEATHER_WITH_LOCATION_AND_DATE:
                result = null;
                break;

            case WEATHER_WITH_LOCATION:
                result = null;
                break;

            case WEATHER:
                result = dbHelper.getReadableDatabase().query(
                        WeatherEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case LOCATION_ID:
                result = null;
                break;

            case LOCATION:
                result = null;
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri:  " + uri);
        }
        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case WEATHER_WITH_LOCATION_AND_DATE:
                return WeatherEntry.CONTENT_ITEM_TYPE;
            case WEATHER_WITH_LOCATION:
                return WeatherEntry.CONTENT_TYPE;
            case WEATHER:
                return WeatherEntry.CONTENT_TYPE;
            case LOCATION:
                return WeatherEntry.CONTENT_TYPE;
            case LOCATION_ID:
                return WeatherEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

}
