package com.cleancoder.sunshine.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 16.09.2014.
 */
public class ForecastParser {

    public Forecast parse(String text) throws JSONException {
        JSONObject root = new JSONObject(text);
        JSONArray forecastArray = root.getJSONArray("list");
        Forecast forecast = new Forecast();
        for (int i = 0; i < forecastArray.length(); ++i) {
            DailyForecast dailyForecast = new DailyForecast();
            JSONObject forecastItem = forecastArray.getJSONObject(i);
            dailyForecast.dateTime(forecastItem.getLong("dt"));
            JSONObject temperature = forecastItem.getJSONObject("temp");
            dailyForecast.min(parseTemperature(temperature, "min"));
            dailyForecast.max(parseTemperature(temperature, "max"));
            JSONArray weather = forecastItem.getJSONArray("weather");
            dailyForecast.description(parseDescription(weather));
            forecast.add(dailyForecast);
        }
        return forecast;
    }

    private static int parseTemperature(JSONObject jsonObject, String name) throws JSONException {
        double temperature = jsonObject.getDouble(name);
        return (int) Math.round(temperature);
    }

    private static String parseDescription(JSONArray jsonArray) throws JSONException {
        List<String> descriptions = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject item = jsonArray.getJSONObject(i);
            descriptions.add(item.getString("main"));
        }
        return StringUtils.join(descriptions, ", ");
    }

}
