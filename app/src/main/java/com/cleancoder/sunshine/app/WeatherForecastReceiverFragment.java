package com.cleancoder.sunshine.app;

import android.os.Bundle;

import com.cleancoder.sunshine.app.util.TaskAbleToHandleException;
import com.cleancoder.sunshine.app.util.UrlBuilder;
import com.cleancoder.sunshine.app.util.activity_fragment.ExceptionDisplayFragment;
import com.cleancoder.sunshine.app.util.activity_fragment.FragmentBuilder;
import com.cleancoder.sunshine.app.util.activity_fragment.FragmentHelper;
import com.cleancoder.sunshine.app.util.activity_fragment.TaskFragment;
import com.cleancoder.sunshine.app.util.HttpRequestor;

import java.io.IOException;

/**
 * Created by Leonid on 10.09.2014.
 */
public class WeatherForecastReceiverFragment extends TaskFragment {

    private static final String KEY_REQUEST = "KEY_REQUEST";
    private static final ForecastParser FORECAST_PARSER = new ForecastParser();

    public static WeatherForecastReceiverFragment newInstance(WeatherForecastRequest request) {
        WeatherForecastReceiverFragment fragment = new WeatherForecastReceiverFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_REQUEST, request);
        fragment.setArguments(args);
        return fragment;
    }

    protected WeatherForecastRequest getWeatherForecastRequest() {
        return getArguments().getParcelable(KEY_REQUEST);
    }

    @Override
    protected void startTask() {
        TaskAbleToHandleException<Forecast> task = new TaskAbleToHandleException<Forecast>() {
            @Override
            protected Forecast doWork() throws Exception {
                return receiveForecast();
            }

            @Override
            protected void onTaskResult(Forecast forecast) {
                onForecastReceived(forecast);
            }

            @Override
            protected void onException(Throwable exception) {
                final WeatherForecastRequest request = getWeatherForecastRequest();
                ExceptionDisplayFragment.ExceptionDisplay exceptionDisplay =
                        new ExceptionDisplayFragment.DefaultExceptionDisplay(exception, new FragmentBuilder() {
                            @Override
                            public FragmentHelper build() {
                                return newInstance(request);
                            }
                        });
                replaceItself(ExceptionDisplayFragment.newInstance(exceptionDisplay));
            }
        };
        task.execute();
    }

    private Forecast receiveForecast() throws IOException {
        HttpRequestor httpRequestor = new HttpRequestor(prepareUrl());
        String response = httpRequestor.request();
        return FORECAST_PARSER.parse(response);
    }

    private String prepareUrl() {
        return UrlBuilder.buildFromUrlAndArguments(
                ForecastWebServer.getUrlToReceiveForecast(),
                getWeatherForecastRequest().getArguments()
        );
    }

    private void onForecastReceived(Forecast forecast) {
        replaceFragment(getContainerId(), ForecastFragment.newInstance(forecast));
    }

}
