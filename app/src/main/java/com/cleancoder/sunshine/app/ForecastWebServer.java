package com.cleancoder.sunshine.app;

/**
 * Created by Leonid on 16.09.2014.
 */
public class ForecastWebServer {

    public static String getUrlToReceiveForecast() {
        return "http://api.openweathermap.org/data/2.5/forecast/daily";
    }

}
