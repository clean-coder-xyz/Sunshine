package com.cleancoder.sunshine.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Leonid on 12.10.2014.
 */
public class ForecastArrayAdapter extends ArrayAdapter<DailyForecast> {

    private static final int LAYOUT_ID = R.layout.forecast_list_view_item;

    private final Forecast forecast;
    private final LayoutInflater layoutInflater;
    private DailyForecastToStringConvertor dailyForecastToStringConvertor;

    public ForecastArrayAdapter(Context context, Forecast forecast) {
        super(context, LAYOUT_ID);
        this.forecast = forecast;
        this.layoutInflater = LayoutInflater.from(context);
        this.dailyForecastToStringConvertor = new SimpleDailyForecastToString();
    }

    public void setDailyForecastToStringConvertor(DailyForecastToStringConvertor dailyForecastToStringConvertor) {
        this.dailyForecastToStringConvertor = dailyForecastToStringConvertor;
    }

    @Override
    public int getCount() {
        return forecast.getForecastsNumber();
    }

    @Override
    public DailyForecast getItem(int position) {
        return forecast.getForecast(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = (convertView != null) ? convertView : layoutInflater.inflate(LAYOUT_ID, null);
        setItemView(itemView, getItem(position));
        return itemView;
    }

    private void setItemView(View itemView, DailyForecast item) {
        TextView textView = (TextView) itemView.findViewById(R.id.text_view);
        String text = dailyForecastToStringConvertor.convertDailyForecastToString(item);
        textView.setText(text);
    }

}
