package com.cleancoder.sunshine.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.cleancoder.sunshine.app.data.WeatherContract.LocationEntry;
import com.cleancoder.sunshine.app.data.WeatherContract.WeatherEntry;
import com.cleancoder.sunshine.app.data.WeatherDbHelper;


/**
 * Created by Leonid Semyonov (clean-coder-xyz) on 25.10.2014.
 */
public class TestProvider extends AndroidTestCase {

    private static final String LOG_TAG = TestProvider.class.getSimpleName();


    public void testDeleteDb() throws Throwable {
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
    }


    public void testGetType() {
        assertGetType(WeatherEntry.CONTENT_TYPE, WeatherEntry.CONTENT_URI);

        String testLocation = "94074";
        assertGetType(WeatherEntry.CONTENT_TYPE,
                WeatherEntry.buildWeatherLocation(testLocation));

        String testDate = "20140612";
        assertGetType(WeatherEntry.CONTENT_ITEM_TYPE,
                WeatherEntry.buildWeatherLocationWithDate(testLocation, testDate));

        assertGetType(LocationEntry.CONTENT_TYPE, LocationEntry.CONTENT_URI);

        assertGetType(LocationEntry.CONTENT_ITEM_TYPE, LocationEntry.buildLocationUri(1L));
    }

    private void assertGetType(String expectedType, Uri uri) {
        String actualType = mContext.getContentResolver().getType(uri);
        assertEquals(expectedType, actualType);
    }


    public void testInsertReadProvider() {
        String testName = "North Pole";
        String testLocationSetting = "99705";
        double testLatitude = 64.772;
        double testLongitude = -147.355;

        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LocationEntry.COLUMN_LOCATION_NAME, testName);
        values.put(LocationEntry.COLUMN_LOCATION_SETTING, testLocationSetting);
        values.put(LocationEntry.COLUMN_LATITUDE, testLatitude);
        values.put(LocationEntry.COLUMN_LONGITUDE, testLongitude);

        long locationRowId;
        locationRowId = db.insertOrThrow(LocationEntry.TABLE_NAME, null, values);

        assertTrue(locationRowId != -1);
        Log.d(LOG_TAG, "New row id:  " + locationRowId);

        String[] columns = {
                LocationEntry._ID,
                LocationEntry.COLUMN_LOCATION_SETTING,
                LocationEntry.COLUMN_LOCATION_NAME,
                LocationEntry.COLUMN_LATITUDE,
                LocationEntry.COLUMN_LONGITUDE
        };

        Cursor cursor = mContext.getContentResolver().query(WeatherEntry.CONTENT_URI, null, null, null, null);

        if (cursor.moveToFirst()) {
            int locationSettingIndex = cursor.getColumnIndex(LocationEntry.COLUMN_LOCATION_SETTING);
            String locationSetting = cursor.getString(locationSettingIndex);

            int nameIndex = cursor.getColumnIndex(LocationEntry.COLUMN_LOCATION_NAME);
            String name = cursor.getString(nameIndex);

            int latitudeIndex = cursor.getColumnIndex(LocationEntry.COLUMN_LATITUDE);
            double latitude = cursor.getDouble(latitudeIndex);

            int longitudeIndex = cursor.getColumnIndex(LocationEntry.COLUMN_LONGITUDE);
            double longitude = cursor.getDouble(longitudeIndex);

            assertEquals(testName, name);
            assertEquals(testLocationSetting, locationSetting);
            assertEquals(testLatitude, latitude);
            assertEquals(testLongitude, longitude);

        } else {
            fail("No values returned");
        }

    }

}
