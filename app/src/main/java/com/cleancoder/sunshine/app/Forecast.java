package com.cleancoder.sunshine.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 10.09.2014.
 */
public class Forecast implements Parcelable {

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel source) {
            return new Forecast(source);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    private List<DailyForecast> forecasts;

    public Forecast() {
        this.forecasts = new ArrayList<DailyForecast>();
    }

    public void add(DailyForecast dailyForecast) {
        forecasts.add(dailyForecast);
    }

    public Forecast(Parcel in) {
        this.forecasts = in.readArrayList(DailyForecast.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeList(forecasts);
    }

    public int getForecastsNumber() {
        return forecasts.size();
    }

    public DailyForecast getForecast(int index) {
        return forecasts.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Forecast)) {
            return false;
        }
        Forecast other = (Forecast) obj;
        return Objects.equal(forecasts, other.forecasts);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(forecasts);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("forecasts", forecasts)
                .toString();
    }

}
