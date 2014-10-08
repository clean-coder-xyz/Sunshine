package com.cleancoder.sunshine.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Leonid on 10.09.2014.
 */
public class ForecastFragment extends BaseApplicationFragment {

    private static final String KEY_FORECAST = "KEY_FORECAST";

    private View contentView;

    public static ForecastFragment newInstance(Forecast forecast) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_FORECAST, forecast);
        fragment.setArguments(args);
        return fragment;
    }

    protected Forecast getForecast() {
        return (Forecast) getArguments().getParcelable(KEY_FORECAST);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_forecast_display, null);
        initContentView();
        return contentView;
    }

    private void initContentView() {
        Forecast forecast = getForecast();
        TextView forecastTextView = (TextView) contentView.findViewById(R.id.forecast);
        forecastTextView.setText(forecast.getText());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.forecast_fragment, menu);
    }

}
