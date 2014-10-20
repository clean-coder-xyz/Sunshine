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
        init();
        return contentView;
    }

    private void init() {
        TextView textView = (TextView) contentView.findViewById(R.id.text_view);
        String detail = getDetail();
        textView.setText(detail);
        String sharedDetailPostfix = getContext().getString(R.string.shared_detail_postfix);
        String sharedDetail = detail + " " + sharedDetailPostfix;
        DetailActivity detailActivity = (DetailActivity) getActivity();
        detailActivity.setShareIntent(prepareSharedIntentFromText(sharedDetail));
    }

    private String getDetail() {
        Intent intent = getActivity().getIntent();
        DailyForecast dailyForecast = intent.getParcelableExtra(KEY_DAILY_FORECAST);
        DailyForecastToStringConverter converter = new SimpleDailyForecastToStringConverter(getContext());
        return converter.convertDailyForecastToString(dailyForecast);
    }

    private Intent prepareSharedIntentFromText(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return intent;
    }

}
