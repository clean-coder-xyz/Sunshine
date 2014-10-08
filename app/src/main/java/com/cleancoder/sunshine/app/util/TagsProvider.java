package com.cleancoder.sunshine.app.util;

import android.support.v4.app.Fragment;

/**
 * Created by Leonid on 08.07.2014.
 */
public class TagsProvider {

    public static String getTagFromFragment(Fragment fragment) {
        return getTagFromFragmentClass(((Object) fragment).getClass());
    }

    public static String getTagFromFragmentClass(Class<?> fragmentClass) {
        return fragmentClass.getName();
    }

}
