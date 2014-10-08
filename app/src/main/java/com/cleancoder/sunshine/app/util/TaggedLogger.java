package com.cleancoder.sunshine.app.util;

import android.util.Log;

/**
 * Created by Leonid on 20.04.2014.
 */
public class TaggedLogger {

    private final String tag;

    public static String getTagOfInstance(Object instance) {
        return getTagOfClass(instance.getClass());
    }

    public static String getTagOfClass(Class<?> someClass) {
        return someClass.getName();
    }

    public static TaggedLogger forInstance(Object instance) {
        return new TaggedLogger(getTagOfInstance(instance));
    }

    public static TaggedLogger forClass(Class<?> someClass) {
        return new TaggedLogger(getTagOfClass(someClass));
    }

    public static TaggedLogger withTag(String tag) {
        return new TaggedLogger(tag);
    }

    private TaggedLogger(String tag) {
        this.tag = tag;
    }

    public void debug(Object obj) {
        Log.d(getTag(), String.valueOf(obj));
    }

    public String getTag() {
        return tag;
    }

    public void exception(String message) {
        Log.e(getTag(), String.valueOf(message));
    }

    public void exception(Throwable exception) {
        Log.e(getTag(), String.valueOf(exception.getMessage()), exception);
    }

    public void exception(Throwable exception, String additionalMessage) {
        Log.e(getTag(), String.valueOf(exception.getMessage()), exception);
        Log.e(getTag(), String.valueOf(additionalMessage));
    }

    public void info(Object obj) {
        Log.i(getTag(), String.valueOf(obj.toString()));
    }

}
