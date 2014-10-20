package com.cleancoder.sunshine.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Leonid Semyonov (clean-coder-xyz) on 19.10.2014.
 */
public class SettingsReader {
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public SettingsReader(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUnits() {
        return loadPreferenceStringValue(R.string.pref_key_units, R.string.pref_default_units);
    }

    private String loadPreferenceStringValue(int keyId, int defaultValueId) {
        String key = context.getString(keyId);
        String defaultValue = context.getString(defaultValueId);
        return sharedPreferences.getString(key, defaultValue);
    }

    public String getUserLocation() {
        return loadPreferenceStringValue(R.string.pref_key_location, R.string.pref_default_location);
    }

}
