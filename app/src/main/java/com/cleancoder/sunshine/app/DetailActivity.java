package com.cleancoder.sunshine.app;

import android.content.Intent;
import android.os.Bundle;



public class DetailActivity extends BaseApplicationActivity {

    public static void insertArguments(Intent intent, DailyForecast dailyForecast) {
        intent.putExtra(DetailFragment.KEY_DAILY_FORECAST, dailyForecast);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            addFragment(R.id.container, DetailFragment.newInstance());
        }
    }

}
