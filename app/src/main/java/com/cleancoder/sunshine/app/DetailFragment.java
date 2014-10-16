package com.cleancoder.sunshine.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DetailFragment extends BaseApplicationFragment {

    public static final String KEY_DAILY_FORECAST = "KEY_DAILY_FORECAST";

    private View contentView;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_detail, null);
        initContentView();
        return contentView;
    }

    private void initContentView() {
        displayForecast();
    }

    private void displayForecast() {
        Intent intent = getActivity().getIntent();
        DailyForecast dailyForecast = intent.getParcelableExtra(KEY_DAILY_FORECAST);
        DailyForecastToStringConverter converter = new SimpleDailyForecastToStringConverter();
        TextView textView = (TextView) contentView.findViewById(R.id.text_view);
        textView.setText(converter.convertDailyForecastToString(dailyForecast));
    }


}
