package com.cleancoder.sunshine.app;

import java.util.List;

/**
 * Created by Leonid on 12.10.2014.
 */
public class StringUtils {

    public static String join(List<String> strings, String separator) {
        StringBuilder builder = new StringBuilder();
        boolean firstLoop = true;
        for (String string : strings) {
            if (firstLoop) {
                firstLoop = false;
            } else {
                builder.append(separator);
            }
            builder.append(string);
        }
        return builder.toString();
    }

}
