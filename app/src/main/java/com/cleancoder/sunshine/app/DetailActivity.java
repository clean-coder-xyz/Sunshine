package com.cleancoder.sunshine.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;


public class DetailActivity extends BaseApplicationActivity {

    private ShareActionProvider shareActionProvider;

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

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.detail, menu);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
        return true;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setShareIntent(Intent shareIntent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                SettingsActivity.start(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
