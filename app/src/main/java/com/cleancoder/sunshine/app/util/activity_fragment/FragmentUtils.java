package com.cleancoder.sunshine.app.util.activity_fragment;

import android.content.Context;
import android.widget.Toast;

import com.cleancoder.sunshine.app.util.TaggedLogger;


/**
 * Created by Leonid on 15.07.2014.
 */
public class FragmentUtils {

    public static void showMessage(android.support.v4.app.Fragment fragment, String message, int toastDuration) {
        TaggedLogger.forInstance(fragment).debug(message);
        Context context = fragment.getActivity();
        if (context == null) {
            return;
        }
        Toast toast = Toast.makeText(context, message, toastDuration);
        toast.show();
    }

}
