package com.cleancoder.sunshine.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leonid on 12.10.2014.
 */
public class ForecastArrayAdapter extends ArrayAdapter<DailyForecast> {

    private static final int LAYOUT_ID = R.layout.forecast_list_view_item;

    private final LayoutInflater layoutInflater;
    private DailyForecastToStringConverter dailyForecastToStringConverter;

    public ForecastArrayAdapter(Context context, List<DailyForecast> forecasts) {
        super(context, LAYOUT_ID, forecasts);
        this.layoutInflater = LayoutInflater.from(context);
        this.dailyForecastToStringConverter = new SimpleDailyForecastToStringConverter();
    }

    public void setDailyForecastToStringConverter(DailyForecastToStringConverter dailyForecastToStringConverter) {
        this.dailyForecastToStringConverter = dailyForecastToStringConverter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = (convertView != null) ? convertView : layoutInflater.inflate(LAYOUT_ID, null);
        setItemView(itemView, getItem(position));
        return itemView;
    }

    private void setItemView(View itemView, DailyForecast item) {
        TextView textView = (TextView) itemView.findViewById(R.id.text_view);
        String text = dailyForecastToStringConverter.convertDailyForecastToString(item);
        textView.setText(text);
    }

}
