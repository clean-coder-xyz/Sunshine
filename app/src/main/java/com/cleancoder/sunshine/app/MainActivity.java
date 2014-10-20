package com.cleancoder.sunshine.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cleancoder.sunshine.app.util.TaggedLoggers;


public class MainActivity extends BaseApplicationActivity {

    public static final String KEY_REQUEST = "KEY_REQUEST";

    private SettingsReader settingsReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingsReader = new SettingsReader(this);
        initRequestIfNeed();
    }

    private void initRequestIfNeed() {
        Intent intent = getIntent();
        if ((intent == null) || intent.hasExtra(KEY_REQUEST)) {
            return;
        }
        intent.putExtra(KEY_REQUEST, prepareRequest());
    }

    private WeatherForecastRequest prepareRequest() {
        return new WeatherForecastRequest()
                .query(settingsReader.getUserLocation())
                .numberOfDays(7)
                .mode(ForecastConstants.Mode.JSON)
                .units(settingsReader.getUnits());
    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }

    public void refresh() {
        WeatherForecastRequest request = getRequest();
        TaggedLoggers.GLOBAL.debug("Request:  " + request);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, WeatherForecastReceiverFragment.newInstance(request))
                .commit();
    }

    public WeatherForecastRequest getRequest() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }
        return (WeatherForecastRequest) intent.getSerializableExtra(KEY_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                SettingsActivity.start(this);
                break;
            case R.id.action_map:
                openPreferredLocationInMap();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        String location = settingsReader.getUserLocation();
        Uri geoLocation = Uri.parse("get:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            TaggedLoggers.GLOBAL.debug("Couldn't call " + location);
        }
    }

}
