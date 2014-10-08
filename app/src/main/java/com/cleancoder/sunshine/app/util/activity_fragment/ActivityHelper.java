package com.cleancoder.sunshine.app.util.activity_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import java.io.Serializable;

/**
 * Created by Leonid on 23.05.2014.
 */
public class ActivityHelper extends ActionBarActivity {

    private static class Key {
        static final String MENU_ITEM_HELPERS = "MENU_ITEM_HELPERS";
    }

    private static final MenuInitializer DUMMY_MENU_INITIALIZER = new MenuInitializer(0) {
        @Override
        public void initialize(MenuItemHelpers menuItemHelpers) {
            // skip
        }
    };

    private MenuItemHelpers menuItemHelpers;
    private boolean isStateWasSaved = false;
    private boolean isDestroyed = false;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        isStateWasSaved = true;
        super.onSaveInstanceState(outState);
        saveState(outState);
    }

    private void saveState(Bundle outState) {
        outState.putSerializable(Key.MENU_ITEM_HELPERS, menuItemHelpers.getState());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuItemHelpers = new MenuItemHelpers();
        getMenuInitializer().initialize(menuItemHelpers);
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
    }

    private void restoreState(Bundle savedInstanceState) {
        Serializable stateOfMenuItemHelpers = savedInstanceState.getSerializable(Key.MENU_ITEM_HELPERS);
        menuItemHelpers.restoreState(stateOfMenuItemHelpers);
    }

    @Override
    protected void onDestroy() {
        isDestroyed = true;
        super.onDestroy();
    }

    public boolean isStateWasSaved() {
        return isStateWasSaved;
    }

    public boolean isActivityWasDestroyed() {
        return isDestroyed;
    }


    public final Fragment findFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public final void addFragment(int containerId, FragmentHelper fragment) {
        addFragment(containerId, fragment, fragment.tag());
    }

    public final void addFragment(int containerId, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment, tag);
        transaction.commit();
    }

    public final void replaceFragment(int containerId, FragmentHelper fragment) {
        replaceFragment(containerId, fragment, fragment.tag());
    }

    public final void replaceFragment(int containerId, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, tag);
        transaction.commit();
    }

    public final void replaceFragmentAndAddToBackstack(int containerId, FragmentHelper fragment) {
        replaceFragmentAndAddToBackstack(containerId, fragment, fragment.tag());
    }

    public final void replaceFragmentAndAddToBackstack(int containerId, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInitializer menuInitializer = getMenuInitializer();
        if (menuInitializer == DUMMY_MENU_INITIALIZER) {
            return super.onCreateOptionsMenu(menu);
        }
        int menuResourceId = menuInitializer.getMenuResourceId();
        getMenuInflater().inflate(menuResourceId, menu);
        menuItemHelpers.setMenu(menu);
        return true;
    }

    protected MenuInitializer getMenuInitializer() {
        return DUMMY_MENU_INITIALIZER;
    }

    public MenuItemHelpers getMenuItemHelpers() {
        return menuItemHelpers;
    }

}
