package com.cleancoder.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cleancoder.sunshine.app.util.ToastUtils;

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
        ListView listView = (ListView) contentView.findViewById(R.id.list_view);
        final ArrayAdapter<DailyForecast> adapter = new ForecastArrayAdapter(
                getActivity(), forecast.getForecasts(), new SimpleDailyForecastToStringConverter(getContext()));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                onDailyForecastClicked(adapter.getItem(position));
            }
        });
    }

    private void onDailyForecastClicked(DailyForecast dailyForecast) {
        DailyForecastToStringConverter dailyForecastToStringConverter =
                new SimpleDailyForecastToStringConverter(getContext());
        ToastUtils.LONG.show(getContext(), "Click on:\n" +
                dailyForecastToStringConverter.convertDailyForecastToString(dailyForecast));
        Intent intent = new Intent(getContext(), DetailActivity.class);
        DetailActivity.insertArguments(intent, dailyForecast);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.forecast_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                onRefresh();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onRefresh() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.refresh();
        }
    }

}
