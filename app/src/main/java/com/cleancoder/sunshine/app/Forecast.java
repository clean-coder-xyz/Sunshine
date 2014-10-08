package com.cleancoder.sunshine.app;

import android.os.Parcel;
import android.os.Parcelable;

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

    private final String text;

    public Forecast() {
        this.text = "";
    }

    public Forecast(String text) {
        this.text = text;
    }

    public Forecast(Parcel in) {
        this.text = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(text);
    }

    public String getText() {
        return text;
    }


}
