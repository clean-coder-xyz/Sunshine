package com.cleancoder.sunshine.app.util.activity_fragment;

import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leonid on 08.08.2014.
 */
public class MenuItemHelpers {

    private Map<Integer, MenuItemHelper> menuItemHelpers;

    public MenuItemHelpers() {
        menuItemHelpers = new HashMap<Integer, MenuItemHelper>();
    }

    public MenuItemHelpers add(int menuItemId, boolean visible) {
        menuItemHelpers.put(menuItemId, new MenuItemHelper(visible));
        return this;
    }

    public void setMenu(final Menu menu) {
        forEach(new OnEachListener() {
            @Override
            public void onEach(Integer menuItemId, MenuItemHelper menuItemHelper) {
                MenuItem menuItem = menu.findItem(menuItemId);
                if (menuItem != null) {
                    menuItemHelper.setMenuItem(menuItem);
                }
            }
        });
    }

    private static interface OnEachListener {
        void onEach(Integer menuItemId, MenuItemHelper menuItemHelper);
    }

    private void forEach(OnEachListener onEachListener) {
        for (Map.Entry<Integer, MenuItemHelper> menuItemIdAndHelper : menuItemHelpers.entrySet()) {
            Integer menuItemId = menuItemIdAndHelper.getKey();
            MenuItemHelper menuItemHelper = menuItemIdAndHelper.getValue();
            onEachListener.onEach(menuItemId, menuItemHelper);
        }
    }

    public void setVisible(int menuItemId, boolean visible) {
        MenuItemHelper menuItemHelper = menuItemHelpers.get(menuItemId);
        menuItemHelper.setVisible(visible);
    }

    public void setOnClickListener(int menuItemId, MenuItem.OnMenuItemClickListener onClickListener) {
        MenuItemHelper menuItemHelper = menuItemHelpers.get(menuItemId);
        menuItemHelper.setOnClickListener(onClickListener);
    }

    public Serializable getState() {
        final Map<Integer, Serializable> state = new HashMap<Integer, Serializable>(menuItemHelpers.size());
        forEach(new OnEachListener() {
            @Override
            public void onEach(Integer menuItemId, MenuItemHelper menuItemHelper) {
                Serializable stateOfMenuItemHelper = menuItemHelper.getState();
                state.put(menuItemId, stateOfMenuItemHelper);
            }
        });
        return (Serializable) state;
    }

    public void restoreState(Serializable state) {
        Map<Integer, Serializable> statesOfMenuItemHelpers = (Map<Integer, Serializable>) state;
        for (Map.Entry<Integer,Serializable> each : statesOfMenuItemHelpers.entrySet()) {
            Integer menuItemId = each.getKey();
            Serializable stateOfMenuItemHelper = each.getValue();
            MenuItemHelper menuItemHelper = menuItemHelpers.get(menuItemId);
            if (menuItemHelper == null) {
                menuItemHelper = new MenuItemHelper(false);
            }
            menuItemHelper.restoreState(stateOfMenuItemHelper);
        }
    }

}
