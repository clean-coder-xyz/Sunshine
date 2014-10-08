package com.cleancoder.sunshine.app;

import com.cleancoder.sunshine.app.util.activity_fragment.NavigationDrawerActivity;

/**
 * Created by Leonid on 02.06.2014.
 */
public class BaseApplicationActivity extends NavigationDrawerActivity {

    @Override
    protected boolean hasNavigationDrawer() {
        return false;
    }

}
