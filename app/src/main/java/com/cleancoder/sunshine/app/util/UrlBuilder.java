package com.cleancoder.sunshine.app.util;

import android.net.Uri;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by Leonid on 05.10.2014.
 */
public class UrlBuilder {

    public static String buildFromUrlAndArguments(String url, List<? extends NameValuePair> arguments) {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        for (NameValuePair argument : arguments) {
            builder.appendQueryParameter(argument.getName(), argument.getValue());
        }
        return builder.toString();
    }

}
