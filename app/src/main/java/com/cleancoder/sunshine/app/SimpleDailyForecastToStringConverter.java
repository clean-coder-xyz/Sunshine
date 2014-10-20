package com.cleancoder.sunshine.app;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leonid on 13.10.2014.
 */
public class SimpleDailyForecastToStringConverter implements DailyForecastToStringConverter {
    private static final String DEGREE_CELSIUS = "\u2103";

    private final Map<String,String> temperatureSymbols;
    private final SettingsReader settingsReader;

    public SimpleDailyForecastToStringConverter(Context context) {
        temperatureSymbols = new HashMap<String,String>();
        addTemperatureSymbols(context, temperatureSymbols);
        settingsReader = new SettingsReader(context);
    }

    private static void addTemperatureSymbols(Context context, Map<String, String> temperatureSymbols) {
        temperatureSymbols.put(context.getString(R.string.pref_entry_value_metric),
                context.getString(R.string.temperature_symbol_metric));
        temperatureSymbols.put(context.getString(R.string.pref_entry_value_imperial),
                context.getString(R.string.temperature_symbol_imperial));
    }

    @Override
    public String convertDailyForecastToString(DailyForecast dailyForecast) {
        final String temperatureSymbol = temperatureSymbols.get(settingsReader.getUnits());
        String dateTime = dateTimeToString(dailyForecast.getDateTime());
        String temperatureMinMax = dailyForecast.getMax() + temperatureSymbol +
                "/" + dailyForecast.getMin() + temperatureSymbol;
        return dateTime + ":  " + temperatureMinMax + "    [ " + dailyForecast.getWeatherDescription() + " ]";
    }

    private String dateTimeToString(long dateTime) {
        Date date=new Date(dateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd");
        return simpleDateFormat.format(date);
    }
}
