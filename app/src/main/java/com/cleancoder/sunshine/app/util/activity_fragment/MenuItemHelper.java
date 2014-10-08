package com.cleancoder.sunshine.app.util.activity_fragment;

import android.view.MenuItem;

import java.io.Serializable;

/**
 * Created by Leonid on 08.08.2014.
 */
public class MenuItemHelper {

    private boolean visible;
    private MenuItem menuItem;
    private MenuItem.OnMenuItemClickListener onClickListener;

    public static MenuItemHelper newVisible() {
        return new MenuItemHelper(true);
    }

    public static MenuItemHelper newInvisible() {
        return new MenuItemHelper(false);
    }

    public MenuItemHelper(boolean visible) {
        this.visible = visible;
        this.menuItem = null;
        this.onClickListener = null;
    }

    public void setOnClickListener(MenuItem.OnMenuItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
        updateOnClickListenerIfNeed();
    }

    private void updateOnClickListenerIfNeed() {
        if (menuItem != null && onClickListener != null) {
            menuItem.setOnMenuItemClickListener(onClickListener);
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        updateVisibilityIfNeed();
    }

    private void updateVisibilityIfNeed() {
        if (menuItem != null) {
            menuItem.setVisible(visible);
        }
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        updateVisibilityIfNeed();
        updateOnClickListenerIfNeed();
    }

    public Serializable getState() {
        return visible;
    }

    public void restoreState(Serializable state) {
        visible = (Boolean) state;
    }

}
