package com.cleancoder.sunshine.app;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Leonid on 13.10.2014.
 */
public class SimpleDailyForecastToStringConverter implements DailyForecastToStringConverter {
    @Override
    public String convertDailyForecastToString(DailyForecast dailyForecast) {
        String dateTime = dateTimeToString(dailyForecast.getDateTime());
        String minMax = dailyForecast.getMax() + "/" + dailyForecast.getMin();
        return StringUtils.join(Arrays.asList(dateTime, dailyForecast.getWeatherDescription(), minMax), " - ");
    }

    private String dateTimeToString(long dateTime) {
        Date date=new Date(dateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd");
        return simpleDateFormat.format(date);
    }
}
