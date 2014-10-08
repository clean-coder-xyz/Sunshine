package com.cleancoder.sunshine.app.util.activity_fragment;

/**
 * Created by Leonid on 09.08.2014.
 */
public abstract class MenuInitializer {

    private final int menuResourceId;

    public MenuInitializer(int menuResourceId) {
        this.menuResourceId = menuResourceId;
    }

    public abstract void initialize(MenuItemHelpers menuItemHelpers);

    public int getMenuResourceId() {
        return menuResourceId;
    }

}
