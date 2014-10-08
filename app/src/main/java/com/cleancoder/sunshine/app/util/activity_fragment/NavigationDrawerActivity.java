package com.cleancoder.sunshine.app.util.activity_fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.cleancoder.sunshine.app.R;


/**
 * Created by Leonid on 07.06.2014.
 */
public abstract class NavigationDrawerActivity extends ActivityHelper {

    private ActionBarDrawerToggle drawerToggle;


    protected abstract boolean hasNavigationDrawer();


    protected void initNavigationDrawer(int idOfDrawerLayout,
                                       int openDrawerContentDescriptorResource,
                                       int closeDrawerContentDescriptorResource) {

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(idOfDrawerLayout);

        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.drawable.ic_drawer,
                openDrawerContentDescriptorResource, closeDrawerContentDescriptorResource) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (hasNavigationDrawer()) {
            drawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (hasNavigationDrawer()) {
            drawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (hasNavigationDrawer()) {
            if (drawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
