package com.cleancoder.sunshine.app;

import android.os.Bundle;

import com.cleancoder.sunshine.app.util.TaggedLoggers;


public class MainActivity extends BaseApplicationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            WeatherForecastRequest request = prepareRequest();
            TaggedLoggers.GLOBAL.debug("Request:  " + request);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, WeatherForecastReceiverFragment.newInstance(request))
                    .commit();
        }
    }

    private WeatherForecastRequest prepareRequest() {
        return WeatherForecastRequest.builder()
                .query("94043")
                .numberOfDays(7)
                .mode(ForecastConstants.Mode.JSON)
                .units(ForecastConstants.Units.METRIC)
                .build();
    }

}
