package com.cleancoder.sunshine.app;

import android.content.Intent;
import android.os.Bundle;

import com.cleancoder.sunshine.app.util.TaggedLoggers;

import java.io.Serializable;


public class MainActivity extends BaseApplicationActivity {

    public static final String KEY_REQUEST = "KEY_REQUEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRequestIfNeed();
        if (savedInstanceState == null) {
            refresh();
        }
    }

    private void initRequestIfNeed() {
        Intent intent = getIntent();
        if ((intent == null) || intent.hasExtra(KEY_REQUEST)) {
            return;
        }
        intent.putExtra(KEY_REQUEST, (Serializable) prepareRequest());
    }

    private WeatherForecastRequest prepareRequest() {
        return new WeatherForecastRequest()
                .query("94043")
                .numberOfDays(7)
                .mode(ForecastConstants.Mode.JSON)
                .units(ForecastConstants.Units.METRIC);
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

}
